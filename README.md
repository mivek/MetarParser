# MetarParser


This java lib provides a Metar decoder.

Use the MetarFacade class and its method decode to decode a metar.
Use the MetarFacade class and its method retrieveFromAirport to get the metar of an airport. This metod take the icao code as parameter.

## Model

### Enumerations

The application contains numerous enumarations to represent datas.
  - CloudType: to represent the type of cloud.
  - CloudQuantity: to represent the amount of clouds.
  - Intensity: to represent the intensity of a meteorological phenomenon.
  - Descriptive: to represent the descriptive of a meteorological phenomenon.
  - Phenomenon: to represent a phenomenon.
  
### Classes

#### Airport
The airport class is composed of
  - Name
  - City
  - Country
  - IATA code
  - ICAO code
  - latitude
  - longitude
  - altitude
  - timezone

####  Cloud
In this application a cloud is composed of 
  - CloudQuantity
  - CloudType (optional)
  - altitude (optional)
  
#### Country
A country is represented by its name.

#### Runway information

The runway information is composed of 
  - The name of the runway
  - The minimal visibility on the runway
  - The maximal visibility on the runway (optional)
  - The trend of the visibility (optional)

#### Visibility

The visibility class is composed of
  - The main visibility
  - The minimal visibility (optional)
  - The direction of the minimal visibility (optional)

#### WeatherCondition
The weather condition is class to represent a meteorological phenomenon.
A weather condition is composed of 
  - an intensity (optional)
  - a descriptive (optional)
  - a list of phenomenon
  
#### Wind
The wind class is composed of 
  - the speed
  - the direction
  - the speed of the gust
  - the lowest variable wind
  - the highest variable wind
  - the unit of the wind's speed
  
#### Time
This class is composed of the hour and the minute.
