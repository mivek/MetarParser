# MetarParser Copilot Instructions

## Project Overview

MetarParser is a Java library for parsing and decoding METAR and TAF aviation weather codes. It is a multi-module Maven project targeting Java 17.

## Build, Test, and Lint Commands

```bash
# Full build with all checks (checkstyle, spotbugs, jacoco coverage)
mvn verify

# Run all tests
mvn test

# Run a single test class
mvn -pl metarParser-parsers -Dtest=MetarParserTest test

# Run a specific test method
mvn -pl metarParser-parsers -Dtest=MetarParserTest#testParse test

# Run checkstyle only
mvn checkstyle:check

# Run mutation testing (pitest) on a specific module
mvn -pl metarParser-parsers org.pitest:pitest-maven:mutationCoverage
```

Coverage thresholds are enforced: 98% instruction, 96% branch, 97% complexity. Builds will fail if coverage drops below these thresholds.

## Module Dependency Order

```
metarParser-commons → metarParser-entities → metarParser-spi → metarParser-parsers → metarParser-services
```

- **commons**: `Regex` utility (PHP-style pattern matching helpers), `Messages` i18n singleton
- **entities**: All model classes (`Metar`, `TAF`, `AbstractWeatherContainer`, trend types) and enumerations
- **spi**: `AirportProvider` interface + default implementations (loaded via Java SPI from `META-INF/services`)
- **parsers**: `MetarParser`, `TAFParser`, and the Command infrastructure
- **services**: `MetarService` and `TAFService` — thin facade layer over parsers, handles HTTP retrieval

## Architecture: Command Pattern for Parsing

Parsers tokenize the raw METAR/TAF string by whitespace, then dispatch each token through a **Command chain**:

1. A `*CommandSupplier` (e.g., `CommonCommandSupplier`, `MetarCommandSupplier`, `TAFCommandSupplier`) iterates its registered `Command` list and calls `canParse(token)` on each.
2. The first matching `Command` executes `execute(container, token)` and mutates the `AbstractWeatherContainer`.
3. Three command namespaces exist:
   - `command.common` — shared between METAR and TAF (wind, visibility, clouds, etc.)
   - `command.metar` — METAR-only (runway, temperature, altimeter)
   - `command.taf` — TAF-only (icing, turbulence)
   - `command.remark` — RMK section parsing

When adding a new parseable token type, create a `Command` implementation in the appropriate sub-package, register it in the corresponding `*CommandSupplier.buildCommands()` method, and add a test class.

## Parser Class Hierarchy

```
AbstractWeatherContainerParser<T, U>
  └── AbstractWeatherCodeParser<T>       (adds tokenization, airport lookup, flags)
        ├── MetarParser                  (parses String → Metar)
        └── TAFParser                    (parses String[] lines → TAF)
```

TAFParser splits on `\n` before being passed in. Trend parsers (`FMTrendParser`, `ProbTrendParser`, etc.) are separate classes instantiated by `TafTrendParserFactory`.

## Key Conventions

### Checkstyle (enforced on all non-test source)
- Every class, method, field, and package must have a Javadoc comment — `JavadocMethod`, `JavadocVariable`, `JavadocType`, and `JavadocPackage` are all enforced.
- Every `package` must have a `package-info.java`.
- All method parameters must be `final` (`FinalParameters` rule).
- No star imports except `org.hamcrest` and `org.junit` (test code only).
- Classes not designed for inheritance must be `final` — checkstyle enforces `DesignForExtension` and `FinalClass`.
- Checkstyle is **not** applied to `*Test.java` files.

### ArchUnit Architecture Tests
- Classes in `command.common`, `command.metar`, `command.taf`, and `command.remark` must **not** depend on the `parser` package.
- All non-interface classes named `*Command` in those packages must implement the local `Command` interface.
- Architecture tests live alongside other tests (e.g., `CommonCommandArchitectureTest`).

### Regex Utility
Use `io.github.mivek.utils.Regex` for all pattern matching — it wraps `java.util.regex` with a PHP-inspired API:
- `Regex.find(pattern, input)` — boolean match
- `Regex.pregMatch(pattern, input)` — returns `String[]` of groups (index 0 = full match)
- `Regex.findString(pattern, input)` — returns capture group 1

### Internationalization
Human-readable strings (wind direction labels, phenomenon names) come from `Messages.getInstance().getString(key)` backed by `internationalization/messages*.properties`. Do not hardcode user-visible strings — add them to all locale files.

### Commit and PR Naming
Follow Conventional Commits: `<type>(<scope>): <subject>` — e.g., `feat(parser): add support for new phenomenon`. PR titles follow the same format. Types: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `build`, `ci`, `chore`, `revert`.

### SPI for Airport Data
`AirportProvider` is loaded via Java SPI (`META-INF/services/io.github.mivek.provider.airport.AirportProvider`). The default implementation reads bundled CSV files (`airports.dat`, `countries.dat`) using double-checked locking for lazy initialization. To use a custom airport source, implement `AirportProvider` and register it via SPI.

### Services Are Singletons; Parsers Are Not
`MetarService.getInstance()` / `TAFService.getInstance()` return static singletons. `MetarParser` and `TAFParser` use plain constructors (the static `getInstance()` on parsers is `@Deprecated(forRemoval = true)`). Prefer constructor instantiation for parsers.

## Weather Provider Architecture

The **Strategy pattern** is used for weather data retrieval to eliminate the NOAA single point of failure and enable multiple data sources.

### WeatherProvider Interface
Located in `io.github.mivek.service.provider`, the interface defines:
- `String retrieveMetar(String icao)` — fetches raw METAR string (without "METAR"/"SPECI" prefix)
- `String retrieveTaf(String icao)` — fetches raw TAF string ready for parsing

Throws `ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO)` when a station is not found.

### Built-in Providers
- **NOAAWeatherProvider** (default): Uses `https://tgftp.nws.noaa.gov/data/`. Handles NOAA-specific TAF reformatting (merging lines when first line is only "TAF").
- **AviationWeatherProvider**: Uses `https://aviationweather.gov/api/data/metar` and `/taf`. Strips "METAR "/"SPECI " prefixes for parser compatibility.

### AbstractWeatherProvider Base Class
Provides shared HTTP utilities (all `protected final`):
- `checkIcao(String icao)` — validates 4-character ICAO codes
- `buildRequest(String url)` — creates HTTP GET request with HTTP/2
- `getHttpResponse(String url)` — executes request and throws `ParseException` on non-200 status

### Using Custom Providers
```java
// Use a specific provider for a single request
MetarService metarService = MetarService.withProvider(new MyWeatherProvider());
Metar m = metarService.retrieveFromAirport("LFPG");

TAFService tafService = TAFService.withProvider(new MyWeatherProvider());
TAF t = tafService.retrieveFromAirport("LFPG");

// Default singletons still use NOAA
MetarService.getInstance().retrieveFromAirport("LFPG");
TAFService.getInstance().retrieveFromAirport("LFPG");
```

### Adding a New Provider
1. Extend `AbstractWeatherProvider` and implement `retrieveMetar()` and `retrieveTaf()`
2. Return raw weather strings in parser-compatible format
3. Create comprehensive tests in `io.github.mivek.service.provider` with 100% branch coverage
4. Add integration tests to `MetarServiceTest` and `TAFServiceTest` using `withProvider(new YourProvider())`
5. See CONTRIBUTING.md for detailed step-by-step instructions
