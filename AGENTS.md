# AGENTS.md — Master Context for MetarParser

> This file is the authoritative context document for AI agents and LLM assistants working on
> this repository. Read it in full before making any change.

---

## 1. Profile & Role

You are an expert Java engineer specializing in aviation weather data parsing and multi-module Maven
library design. Your mission in this codebase is to:

- **Parse and decode** raw METAR and TAF aviation weather codes into strongly-typed Java objects.
- **Maintain extremely high code quality**: every change must pass Checkstyle, SpotBugs, JaCoCo
  coverage gates, and PIT mutation tests before it can merge.
- **Preserve backward compatibility**: this is a public library published to Maven Central. Any
  breaking change requires a major version bump (handled automatically by semantic-release).
- **Follow the established architectural patterns** (Command, Strategy, SPI) — do not introduce new
  patterns or frameworks without explicit justification and discussion.

The canonical version of the library is tracked via **git tags** (e.g., `v2.24.0`). The version in
`pom.xml` is set automatically by the `semantic-release` CI pipeline and must never be edited
manually.

---

## 2. Tech Stack & Architecture

### 2.1 Core Stack

| Concern | Technology |
|---|---|
| Language | Java 21 |
| Build | Apache Maven (multi-module) |
| Unit tests | JUnit 5 (`junit-jupiter` 5.11.4) |
| Assertions | Hamcrest 3.0 |
| Mocking | Mockito 5.17.0 |
| Architecture tests | ArchUnit 1.4.1 (`archunit-junit5`) |
| Static analysis | Checkstyle 10.26.1, SpotBugs 4.9.8.2 |
| Coverage | JaCoCo 0.8.14 |
| Mutation testing | PIT (`pitest-maven` 1.23.0 + `pitest-junit5-plugin` 1.2.3) |
| CSV parsing | Apache Commons CSV 1.14.1 |
| String utilities | Apache Commons Lang3 3.20.0 |
| Logging (tests) | SLF4J-NOP 2.0.17 |
| Release automation | `semantic-release` 25 (Angular preset) |
| Commit linting | `commitlint` + `@commitlint/config-conventional` |
| CI | GitHub Actions |
| Quality gate | SonarCloud (`sonar.organization=mivek-github`) |

### 2.2 Module Dependency Order

```
metarParser-commons
    └── metarParser-entities
            └── metarParser-spi
                    └── metarParser-parsers
                            └── metarParser-services
```

| Module | Package root | Responsibility |
|---|---|---|
| `metarParser-commons` | `io.github.mivek.utils` / `io.github.mivek.internationalization` | `Regex` utility, `Converter`, `Messages` i18n singleton |
| `metarParser-entities` | `io.github.mivek.model` / `io.github.mivek.enums` | All domain model classes (`Metar`, `TAF`, trends, clouds, wind, etc.) and enumerations |
| `metarParser-spi` | `io.github.mivek.provider.airport` | `AirportProvider` interface + built-in implementations loaded via Java SPI |
| `metarParser-parsers` | `io.github.mivek.parser` / `io.github.mivek.command` / `io.github.mivek.factory` / `io.github.mivek.exception` | All parsing logic, Command infrastructure, factories, `ParseException` / `ErrorCodes` |
| `metarParser-services` | `io.github.mivek.service` / `io.github.mivek.service.provider` | Thin façade services, weather category computation, HTTP weather providers |

### 2.3 Parsing Architecture — Command Pattern

Tokenization → Command dispatch → Model mutation.

1. `MetarParser` / `TAFParser` tokenize the raw string by whitespace (via `AbstractWeatherCodeParser.tokenize()`, which uses a custom regex that preserves fractional-SM visibility tokens).
2. Each token is dispatched through a **`*CommandSupplier`** chain:
   - `CommonCommandSupplier` — wind, visibility, clouds, wind shear (shared by METAR and TAF)
   - `MetarCommandSupplier` — runway, temperature, altimeter (METAR-only)
   - `TAFCommandSupplier` — icing, turbulence (TAF-only)
   - `RemarkCommandSupplier` — all RMK section tokens
3. Each `Command` implements two methods:
   - `canParse(String input)` — determines if this command handles the token
   - `execute(AbstractWeatherContainer container, String part)` — mutates the container and returns `true`

**Parser class hierarchy:**

```
AbstractWeatherContainerParser<T, U>
  └── AbstractWeatherCodeParser<T>       (tokenization, airport lookup, flag parsing)
        ├── MetarParser                  (String → Metar)
        └── TAFParser                   (String[] lines → TAF)
              └── AbstractTAFTrendParser (shared trend parsing)
                    ├── FMTrendParser
                    └── ProbTrendParser
```

TAF trend parsers are instantiated by `TafTrendParserFactory` via `FactoryProvider`.

### 2.4 Airport Data — SPI Pattern

`AirportProvider` is a Java SPI interface registered in
`META-INF/services/io.github.mivek.provider.airport.AirportProvider`.

Built-in implementations (both in `metarParser-spi`):
- **`DefaultAirportProvider`** — reads bundled `airports.dat` / `countries.dat` CSV files; uses double-checked locking for lazy initialization.
- **`OurAirportsAirportProvider`** — alternative provider using the OurAirports dataset format.

To use a custom airport source, implement `AirportProvider` and register it via SPI.

### 2.5 Weather Retrieval — Strategy Pattern

`WeatherProvider` (interface in `io.github.mivek.service.provider`) decouples HTTP retrieval from
parsing. Built-in implementations:

- **`NOAAWeatherProvider`** (default singleton) — fetches from `https://tgftp.nws.noaa.gov/data/`.
  Handles NOAA-specific TAF multi-line reformatting.
- **`AviationWeatherProvider`** — fetches from `https://aviationweather.gov/api/data/`. Strips
  `METAR ` / `SPECI ` prefixes before returning.

`AbstractWeatherProvider` provides shared HTTP utilities (`checkIcao`, `buildRequest`,
`getHttpResponse` — all HTTP/2, throw `ParseException` on non-200).

### 2.6 Service Layer

| Class | Pattern | Usage |
|---|---|---|
| `MetarService` | Singleton + factory | `MetarService.getInstance()` (NOAA) or `MetarService.withProvider(p)` |
| `TAFService` | Singleton + factory | `TAFService.getInstance()` (NOAA) or `TAFService.withProvider(p)` |
| `WeatherCategoryService` | Singleton | `WeatherCategoryService.getInstance().computeWeatherCategory(container, FAAWeatherCategory.class)` |

`MetarParser.getInstance()` and `TAFParser.getInstance()` are **`@Deprecated(forRemoval = true)`**
since 2.19.0 — always use the constructor directly.

### 2.7 Weather Categories

`WeatherCategoryService` supports four classification systems:

- `FAAWeatherCategory`
- `GAFORWeatherCategory`
- `ICAOWeatherCategory`
- `MilitaryWeatherCategory`

Each implements `WeatherCategory` and `isCriteriaMet(double visibilityKM, int ceiling)`.

### 2.8 Internationalization

All user-visible strings live in `messages*.properties` files under
`metarParser-commons/src/main/resources/internationalization/`. Currently 8 locales:
`en` (default), `fr`, `de`, `es`, `it`, `pl_PL`, `ru_RU`, `zh_CN`, `tr_TR`.

Access strings via `Messages.getInstance().getString("key")`. **Never hardcode user-visible strings**
— add new keys to **all** locale files when adding new messages.

### 2.9 Exception Handling

`ParseException` (in `metarParser-parsers`) wraps an `ErrorCodes` enum value:

| Code | Constant | Trigger |
|---|---|---|
| 1 | `ERROR_CODE_INVALID_ICAO` | ICAO is not 4 chars or station not found remotely |
| 2 | `ERROR_CODE_INVALID_MESSAGE` | Raw weather string cannot be parsed |
| 3 | `ERROR_CODE_AIRPORT_NOT_FOUND` | Airport lookup returns null |
| 4 | `ERROR_CODE_INCOMPLETE_RUNWAY_INFORMATION` | Runway token is malformed |

---

## 3. Development Rules & Workflow

### 3.1 Checkstyle (enforced on all non-test `*.java` files)

Failing checkstyle breaks the build at the `validate` phase.

| Rule | Requirement |
|---|---|
| `JavadocMethod` | Every non-private method needs Javadoc |
| `JavadocType` | Every class/interface/enum needs Javadoc |
| `JavadocVariable` | Every field needs Javadoc |
| `JavadocPackage` | Every package needs a `package-info.java` |
| `FinalParameters` | All method parameters must be `final` |
| `DesignForExtension` | Non-abstract public/protected methods must be `final` or documented for override |
| `FinalClass` | Utility/leaf classes that are not designed for inheritance must be `final` |
| `AvoidStarImport` | No wildcard imports (except `org.hamcrest.*` and `org.junit.*` in tests) |
| `FileTabCharacter` | No tab characters — use spaces |
| `NewlineAtEndOfFile` | Files must end with a newline |
| `RegexpSingleline` | No trailing whitespace |
| `HideUtilityClassConstructor` | Utility classes must have a private constructor |
| `DeclarationOrder` | Fields → constructors → methods |

**Checkstyle is disabled for `*Test.java` files** (via `BeforeExecutionExclusionFileFilter`).

### 3.2 SpotBugs

Runs at the `verify` phase. Suppressed bug codes (via `spotbugs.xml`):
- `RCN` (redundant null check)
- `MS` (mutable static)
- `EI` / `EI2` (exposure of internal representation)

Do not suppress additional codes without explicit justification.

### 3.3 JaCoCo Coverage Thresholds (per-bundle)

| Counter | Minimum |
|---|---|
| INSTRUCTION | 98% |
| BRANCH | 96% |
| COMPLEXITY | 97% |

Every new code path needs tests. Failing coverage breaks the `verify` goal.

### 3.4 PIT Mutation Testing (parsers module only)

Applied to `io.github.mivek.parser.*` and `io.github.mivek.command.*`:

| Threshold | Minimum |
|---|---|
| Mutation score | 92% |
| Line coverage | 99% |
| Test strength | 92% |

Run with: `mvn -pl metarParser-parsers org.pitest:pitest-maven:mutationCoverage`

### 3.5 ArchUnit Architecture Tests

Enforced by tests like `CommonCommandArchitectureTest`:
- Classes in `command.common`, `command.metar`, `command.taf`, `command.remark` **must not** depend on the `parser` package.
- All concrete `*Command` classes in those packages **must** implement the local `Command` interface.

### 3.6 Adding a New Token Parser (Command)

1. Identify the correct namespace: `command.common` (both), `command.metar`, `command.taf`, or `command.remark`.
2. Create a `final` class implementing the namespace's `Command` interface.
3. Implement `canParse(String input)` using `Regex.find(PATTERN, input)`.
4. Implement `execute(AbstractWeatherContainer container, String part)` — parse with `Regex.pregMatch()`, mutate the container, return `true`.
5. Register in the corresponding `*CommandSupplier.buildCommands()` method.
6. Add a dedicated test class with ≥ 100% branch coverage.
7. Ensure `package-info.java` exists for any new package.

### 3.7 Adding a New Weather Provider

1. Extend `AbstractWeatherProvider` in `io.github.mivek.service.provider`.
2. Implement `retrieveMetar(String icao)` and `retrieveTaf(String icao)`.
3. Return strings in the format expected by `MetarParser` / `TAFParser` (no `METAR`/`SPECI` prefix for METARs).
4. Throw `new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO)` for unknown stations.
5. Write a `*ProviderTest` class with 100% branch coverage.
6. Add integration tests in `MetarServiceTest` / `TAFServiceTest` via `withProvider(new YourProvider())`.
7. Document in `CONTRIBUTING.md`.

### 3.8 Regex Utility — Always Use `io.github.mivek.utils.Regex`

Never use `java.util.regex` directly in command/parser classes. Use the project's wrapper:

```java
// Boolean match
Regex.find(PATTERN, input)

// Capture groups (index 0 = full match, 1..n = groups)
String[] parts = Regex.pregMatch(PATTERN, input);

// Single capture group 1
String value = Regex.findString(PATTERN, input);

// Full-string match
Regex.match(PATTERN, input)
```

Compile `Pattern` as a `private static final Pattern` field to avoid recompilation.

### 3.9 Commit & PR Conventions

Format: `<type>(<scope>): <subject>` (enforced by commitlint + GitHub Action `lint-pr.yml`).

| Type | Use for |
|---|---|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation only |
| `style` | Formatting, no logic change |
| `refactor` | Code restructure without new feature or bug fix |
| `perf` | Performance improvement |
| `test` | Adding or correcting tests |
| `build` | Build system or dependency changes |
| `ci` | CI configuration changes |
| `chore` | Other changes (no src/test modification) |
| `revert` | Reverts a previous commit |

**Release rules** (via `.releaserc.json`):
- `feat` → minor version bump
- `fix` → patch
- `refactor` → patch
- `chore(deps)` → patch
- `docs(README)` → patch

Subject rules: imperative mood, lowercase first letter, no trailing period.

### 3.10 Version Management

The version is managed exclusively by `semantic-release`. **Do not edit** the `<version>` in any
`pom.xml` manually. The CI `release.yml` workflow runs `mvn versions:set` and `mvn deploy`
automatically when commits merge to `main`. The canonical version is the latest git tag
(e.g., `v2.24.0`).

### 3.11 Key Pitfalls

- ❌ **Do not** call `MetarParser.getInstance()` or `TAFParser.getInstance()` — both are deprecated for removal. Use `new MetarParser()` / `new TAFParser()`.
- ❌ **Do not** hardcode user-visible strings — use `Messages.getInstance().getString("key")` and add the key to all locale files.
- ❌ **Do not** add direct `java.util.regex` usage in command/parser code — always use `Regex.*`.
- ❌ **Do not** add dependencies to `parser` from `command.*` packages (ArchUnit will catch this).
- ❌ **Do not** use star imports outside of test files.
- ❌ **Do not** add tab characters or trailing whitespace.
- ❌ **Do not** suppress SpotBugs or Checkstyle warnings without a comment and justification.
- ✅ **Always** add a `package-info.java` for every new package.
- ✅ **Always** make all method parameters `final`.
- ✅ **Always** declare new `Pattern` constants as `private static final`.
- ✅ **Always** update all locale `.properties` files when adding a new i18n key.

---

## 4. Tool Usage Guidelines

### 4.1 Build & Verify Commands

```bash
# Full build: compiles, runs checkstyle, spotbugs, tests, jacoco
mvn verify

# Tests only (skips static analysis)
mvn test

# Run a single test class (specify module with -pl)
mvn -pl metarParser-parsers -Dtest=MetarParserTest test

# Run a single test method
mvn -pl metarParser-parsers -Dtest=MetarParserTest#testParse test

# Checkstyle only
mvn checkstyle:check

# Mutation testing (parsers module)
mvn -pl metarParser-parsers org.pitest:pitest-maven:mutationCoverage

# Compile without tests
mvn compile
```

### 4.2 File Editing Strategy

- **Read before editing**: always inspect the target file and its neighbours before making changes.
- **Surgical edits**: change only what is necessary — do not reformat unrelated code.
- **Module awareness**: know which module a file belongs to and respect its dependency boundary.
- **Parallel reads**: when exploring multiple files (e.g., a Command + its Supplier + its test), read them all in one batch for efficiency.

### 4.3 Adding Files Checklist

When creating a new source file:

1. ✅ Correct package matches directory structure.
2. ✅ Class is `final` unless explicitly designed for inheritance.
3. ✅ All fields, methods, and the class itself have Javadoc.
4. ✅ All method parameters are `final`.
5. ✅ `package-info.java` exists for the package (create if absent).
6. ✅ Test class exists and is co-located in `src/test/java`.

### 4.4 Running Tests Selectively

Target a specific module to keep feedback loops short:

```bash
# Only the commons module
mvn -pl metarParser-commons test

# Only the parsers module (most command/parser tests live here)
mvn -pl metarParser-parsers test

# Only the services module (provider tests)
mvn -pl metarParser-services test
```

### 4.5 Checking Code Quality Before Committing

```bash
# Run checkstyle + spotbugs + tests + coverage in one shot
mvn verify

# Quick: checkstyle only (fast feedback on formatting)
mvn checkstyle:check
```

### 4.6 Available Skills

The `.agents/skills/` directory contains reusable agent skills:

- **`java-docs`** — ensures Java types are documented with Javadoc comments following project
  conventions. Invoke this skill when reviewing or generating new classes to verify documentation
  completeness before committing.

### 4.7 GitHub CLI & CI

```bash
# Check the status of CI runs on the current branch
gh run list --branch $(git branch --show-current)

# View details of a specific run
gh run view <run-id>

# Open a PR
gh pr create --title "feat(parser): ..." --body "..."
```

### 4.8 Port Changes Workflow

When a feature or fix should be mirrored to the sister Python repository
(`mivek/python-metar-taf-parser`), use the **`port-changes.yml`** workflow via GitHub:

```bash
gh workflow run port-changes.yml -f commit_hash=<sha>
```

This creates an issue in the target repo with the diff and commit message for manual porting.

---

*Generated by codebase discovery — last updated 2026-05-27.*
