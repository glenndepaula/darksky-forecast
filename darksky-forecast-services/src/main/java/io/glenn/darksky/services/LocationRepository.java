package io.glenn.darksky.services;

import io.glenn.darksky.data.Location;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;

public interface LocationRepository extends MongoRepository<Location, Long> {

    public Location findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

}
