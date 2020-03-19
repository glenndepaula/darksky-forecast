# Programming Exercise - Dark Sky API

Displays weather forecast in a table for specified locaitons. Used the following:

1. [Spring Boot w/ Maven](https://start.spring.io/)
2. [Dark Sky API](https://darksky.net/) client using [OpenAPI Tools](https://github.com/OpenAPITools)
3. Free-tier MongoDB database from [mLab](https://mlab.com/home)
4. [Bootstrap](https://getbootstrap.com/) as CSS framwork/toolkit

## High-level Design

The web application (darksky-forecast-web) is a Spring Boot application that utilizes the service library (darksky-forecast-services) to quuery MongoDB weather data. If no data is found for a certain location and time, the service library will make and API call using the java-client library (darksky-forecast-java-client). It will then save this to the database before displaying the fetched data to the user interface. On bootup, the application populates the database with a predefined list of locations for this exercise.

## Usage

Clone this repository to your local machine. Using the command-line terminal, change directory into your working copy.

Edit the following files and provide the necessary configuration values.

* darksky-forecast-web\src\main\resources\application.properties

```
    darksky.api.key=<DARKSKY-API-KEY>

    spring.data.mongodb.uri=mongodb+srv://<MONGO-USER>:<MONGO-PASS>@cluster0- ...
```

* darksky-forecast-java-client\src\test\resources\io\glenn\darksky\api\client\ForecastApiTest.properties

```
    darksky.api.key=<DARKSKY-API-KEY>
```

Use Maven to compile and install the libraries needed by the web application.

```sh
$ mvn clean install
```

Run the web application as a Spring Boot application.

```sh
$ mvn spring-boot:run -pl darksky-forecast-web
```

Once the embedded Tomcat is running, open a web browser (i.e. Google Chrome) and point it to <a href="http://localhost:8080/">http://localhost:8080/</a>. The user will be redirected to the weather forecast of a default location.

## User interface

The user interface consists of two components. The center/main panel contains the current weather forecast for a given location, while the locations panel contains a list of predefined locations. Clicking the link to a certain location directs the user to the current weather forecast of the selected location.

Weather forecasts contain these basic information:

1. The name of the location
2. A summary of the weather forecast
3. A tabular hourly foreecast including:
   1. Time of day
   2. Temperature
   3. Short description of the forecast

## Things left to do

1. Proper housekeeping of data older than three days (preferably reactive)
2. Proper Mongo query to prevent duplicate weather data
3. Use of weather icons
