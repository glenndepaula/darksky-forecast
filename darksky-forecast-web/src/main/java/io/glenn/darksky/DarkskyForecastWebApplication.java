package io.glenn.darksky;

import io.glenn.darksky.data.Location;
import io.glenn.darksky.services.LocationRepository;
import io.glenn.darksky.services.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DarkskyForecastWebApplication implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    public static void main(String[] args) {
        SpringApplication.run(DarkskyForecastWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear data first
        locationRepository.deleteAll();

        Object[][] locations = new Object[][] {{"Campbell, CA", 37.287659, -121.942429},
				{"Omaha, NE", 41.256538, -95.934502},
				{"Austin, TX", 30.267153, -97.743057},
				{"Niseko, Japan", 42.792605, 140.678850},
				{"Nara, Japan", 34.685085, 135.804993},
				{"Jakarta, Indonesia", -6.2088, 106.8456}};
        Location location;
		for(Object[] locationObj : locations) {
			location = new Location();
			location.setName((String) locationObj[0]);
			location.setLatitude(BigDecimal.valueOf((double) locationObj[1]));
			location.setLongitude(BigDecimal.valueOf((double) locationObj[2]));
			locationRepository.save(location);
		}
    }
}
