# SPI Module

This module contains interfaces and implementations for the SPI pattern.

## Airport source

The interface `AirportProvider` defines the contract needed for the implementation. 
Implementations should return a map of airport. The key should be the ICAO of the station and the value the `Airport` object.

### Default implementations

The project provides 2 default implementations:

-   `DefaultAirportProvider` : The implementation uses a file contained in the project `airport.dat` for [openflights](https://openflights.org/).
-   `OurAirportsAirportProvider`: This implementation is based on [ourairports](https://ourairports.com) onlines csv of countries and airport. To use this implementation make sure you have internet access.

### Specify the implementation

It is possible to set the implementation to use according to the [SPI pattern](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html).
Create a file named `io.github.mivek.provider.airport.AirportProvider` in the folder `META-INF/services` of your application.
In this file write the fully qualified name of the implementation you want to use.

Example

```(java)
io.github.mivek.provider.airport.impl.OurAirportsAirportProvider
```

### Add your own implementation

You can use your own implementation of `AirportProvider`.
Create a class extending the interface `AirportProvider`, and follow the steps written [above](#specify-the-implementation).
