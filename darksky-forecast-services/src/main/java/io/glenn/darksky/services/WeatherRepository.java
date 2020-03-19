package io.glenn.darksky.services;

import io.glenn.darksky.data.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<Weather, Long> {


}
