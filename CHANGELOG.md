# [2.24.0](https://github.com/mivek/MetarParser/compare/v2.23.0...v2.24.0) (2026-04-24)


### Features

* **entities:** add LengthUnit to entities ([c99d57c](https://github.com/mivek/MetarParser/commit/c99d57c2c915a4deb6409d78cbb4c70439fc2924))

# [2.23.0](https://github.com/mivek/MetarParser/compare/v2.22.0...v2.23.0) (2026-04-24)


### Features

* **services:** add WeatherProvider pattern with NOAA and AviationWeather providers ([dba7656](https://github.com/mivek/MetarParser/commit/dba7656c397ff38aad424f76359d4700f12adfcb))

# [2.22.0](https://github.com/mivek/MetarParser/compare/v2.21.2...v2.22.0) (2026-04-23)


### Features

* add missing implementations to parser ([14189a0](https://github.com/mivek/MetarParser/commit/14189a0ebdd3b9d2b62530369ca00e7635c884e6))

## [2.21.2](https://github.com/mivek/MetarParser/compare/v2.21.1...v2.21.2) (2026-04-22)


### Bug Fixes

* **security:** prevent script injection by assigning inputs.commit_hash to env var ([e2fe222](https://github.com/mivek/MetarParser/commit/e2fe22228b5dd6f3dd893ae13d5ce9396b00ea71))

## [2.21.1](https://github.com/mivek/MetarParser/compare/v2.21.0...v2.21.1) (2026-04-22)


### Bug Fixes

* french translation ([9520638](https://github.com/mivek/MetarParser/commit/9520638ca444f9b455f4b0b856f10bf095479919))

# [2.21.0](https://github.com/mivek/MetarParser/compare/v2.20.13...v2.21.0) (2026-04-20)


### Features

* add length unit to RunwayInfo ([45f15fb](https://github.com/mivek/MetarParser/commit/45f15fbe3691dbbfdd1903e57904f5079be779a4))

## [2.20.13](https://github.com/mivek/MetarParser/compare/v2.20.12...v2.20.13) (2026-04-08)


### Bug Fixes

* **parser:** improve runway parsing to properly handle cases when data is only partially provided ([9856a56](https://github.com/mivek/MetarParser/commit/9856a5607dbda3d877779033a96be3ed8b19f20a))

## [2.20.12](https://github.com/mivek/MetarParser/compare/v2.20.11...v2.20.12) (2026-04-06)

## [2.20.11](https://github.com/mivek/MetarParser/compare/v2.20.10...v2.20.11) (2026-04-06)

## [2.20.10](https://github.com/mivek/MetarParser/compare/v2.20.9...v2.20.10) (2026-03-29)

## [2.20.9](https://github.com/mivek/MetarParser/compare/v2.20.8...v2.20.9) (2026-03-29)

## [2.20.8](https://github.com/mivek/MetarParser/compare/v2.20.7...v2.20.8) (2026-03-14)

## [2.20.7](https://github.com/mivek/MetarParser/compare/v2.20.6...v2.20.7) (2026-03-14)

## [2.20.6](https://github.com/mivek/MetarParser/compare/v2.20.5...v2.20.6) (2026-02-22)

## [2.20.5](https://github.com/mivek/MetarParser/compare/v2.20.4...v2.20.5) (2026-02-15)

## [2.20.4](https://github.com/mivek/MetarParser/compare/v2.20.3...v2.20.4) (2026-02-01)

## [2.20.3](https://github.com/mivek/MetarParser/compare/v2.20.2...v2.20.3) (2026-01-04)

## [2.20.2](https://github.com/mivek/MetarParser/compare/v2.20.1...v2.20.2) (2026-01-03)

## [2.20.1](https://github.com/mivek/MetarParser/compare/v2.20.0...v2.20.1) (2025-11-29)


### Bug Fixes

* handle cloud quantity CLR the same way as SKC ([6f54567](https://github.com/mivek/MetarParser/commit/6f5456724a1def58abd34d699af2cadccab239b3))

# [2.20.0](https://github.com/mivek/MetarParser/compare/v2.19.0...v2.20.0) (2025-07-19)


### Features

* deprecate usage of singleton pattern ([86f8f51](https://github.com/mivek/MetarParser/commit/86f8f513055532ac14b55c901aa62e06ff9441f5))

# [2.19.0](https://github.com/mivek/MetarParser/compare/v2.18.1...v2.19.0) (2025-07-11)


### Features

* parse TAF messages without delivery time ([b5ca5c0](https://github.com/mivek/MetarParser/commit/b5ca5c0cc60ca080576f44dbb9ba96d08869df2c))

## [2.18.1](https://github.com/mivek/MetarParser/compare/v2.18.0...v2.18.1) (2025-06-30)


### Bug Fixes

* made the runway visibility ranges integer ([49ba715](https://github.com/mivek/MetarParser/commit/49ba71535d6dc5bfad7f7e6818293153a1b5e944))

# [2.18.0](https://github.com/mivek/MetarParser/compare/v2.17.2...v2.18.0) (2025-06-12)


### Features

* improve performance in AirportSupplier by resolving the provider once in the constructor ([97374b4](https://github.com/mivek/MetarParser/commit/97374b411905efa9f47aac26e17627fc8e79d340))

## [2.17.2](https://github.com/mivek/MetarParser/compare/v2.17.1...v2.17.2) (2025-04-06)


### Bug Fixes

* **WeatherCategoryService:** prevent NPE when the height of a cloud is not set ([dbf3bf8](https://github.com/mivek/MetarParser/commit/dbf3bf84808da80ea09dec14530078300b5cd4ab))

## [2.17.2](https://github.com/mivek/MetarParser/compare/v2.17.1...v2.17.2) (2025-04-06)


### Bug Fixes

* **WeatherCategoryService:** prevent NPE when the height of a cloud is not set ([dbf3bf8](https://github.com/mivek/MetarParser/commit/dbf3bf84808da80ea09dec14530078300b5cd4ab))
