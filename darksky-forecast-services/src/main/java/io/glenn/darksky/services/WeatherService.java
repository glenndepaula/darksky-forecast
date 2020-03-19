package io.glenn.darksky.services;

import io.glenn.darksky.api.client.ForecastApi;
import io.glenn.darksky.api.client.model.DataPoint;
import io.glenn.darksky.api.client.model.Forecast;
import io.glenn.darksky.data.HourlyForecast;
import io.glenn.darksky.data.Location;
import io.glenn.darksky.data.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ForecastApi forecastApi;

    @Value("${darksky.api.key}")
    private String key;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Weather getWeather(Location location) {
        return getWeather(location, OffsetDateTime.now());
    }

    public Weather getWeather(Location location, OffsetDateTime dateTime) {
        OffsetDateTime startDateTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond(dateTime.atZoneSameInstant(ZoneId.of("GMT")).toEpochSecond()), ZoneId.of("GMT"));
        OffsetDateTime endDateTime = startDateTime.plusHours(24L).minusNanos(1L);

        Query query = new Query().addCriteria(Criteria.where("time").gte(startDateTime).lt(endDateTime));
        Weather weather = mongoTemplate.findOne(query, Weather.class);

        if(weather == null) {
            List<String> exclude = Arrays.asList("currently", "flags");
            Forecast forecast = forecastApi.getLocalForecast(key, location.getLatitude() + "," + location.getLongitude(), exclude, null, null, null);

            weather = new Weather();
            weather.setLocation(locationRepository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude()));
            weather.setTimezone(forecast.getTimezone());
            weather.setSummary(forecast.getDaily().getSummary());
            DataPoint dataPoint = forecast.getDaily().getData().get(0);
            weather.setTime(convertToJavaGmt(dataPoint.getTime()));
            weather.setTemperatureHigh(dataPoint.getTemperatureHigh());
            weather.setTemperatureLow(dataPoint.getTemperatureLow());
            weather.setSunriseTime(convertToJavaGmt(dataPoint.getSunriseTime()));
            weather.setSunsetTime(convertToJavaGmt(dataPoint.getSunsetTime()));

            HourlyForecast hourlyForecast;
            for(DataPoint dp : forecast.getHourly().getData()) {
                hourlyForecast = new HourlyForecast();
                hourlyForecast.setSummary(dp.getSummary());
                hourlyForecast.setTime(convertToJavaGmt(dp.getTime()));
                hourlyForecast.setTimezone(weather.getTimezone());
                hourlyForecast.setTemperature(dp.getTemperature());
                hourlyForecast.setWeather(weather);
                weather.addHourlyForecast(hourlyForecast);
            }
        }

        return weather;
    }

    private OffsetDateTime convertToJavaGmt(org.threeten.bp.OffsetDateTime dateTime) {
        return OffsetDateTime.ofInstant(
                Instant.ofEpochSecond(
                        org.threeten.bp.OffsetDateTime.ofInstant(
                                dateTime.toInstant(),
                                dateTime.getOffset()
                        ).toEpochSecond()
                ),
                ZoneId.of("GMT")
        );
    }
}
