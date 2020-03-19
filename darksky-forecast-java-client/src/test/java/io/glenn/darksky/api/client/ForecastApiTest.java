package io.glenn.darksky.api.client;

import io.glenn.darksky.api.client.model.Forecast;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * API tests for ForecastApi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource
public class ForecastApiTest {

    @Value("${darksky.api.key}")
    private String key;

    private final ForecastApi api = new ForecastApi();

    /**
     * Forecast Request
     *
     * A Forecast Request returns the current weather conditions, a minute-by-minute forecast for the next hour
     * (where available), an hour-by-hour forecast for the next 48 hours, and a day-by-day forecast for the next week.
     */
    @Test
    public void getLocalForecastTest() {
        double latitude = 14.5995;
        double longitude = 120.9842;
        String latLong = latitude + "," + longitude;
        List<String> exclude = Collections.singletonList("currently");
        String extend = null;
        String lang = null;
        String units = null;
        Forecast response = api.getLocalForecast(key, latLong, exclude, extend, lang, units);

        Assert.assertEquals(BigDecimal.valueOf(latitude), response.getLatitude());
        Assert.assertEquals(BigDecimal.valueOf(longitude), response.getLongitude());
        Assert.assertNull(response.getCurrently());
        Assert.assertNotNull(response.getHourly());
    }


    /**
     * Time Machine Request
     *
     * A Time Machine Request returns the observed (in the past) or forecasted (in the future) hour-by-hour weather and
     * daily weather conditions for a particular date. The daily data block will contain a single data point referring
     * to the requested date.
     */
    @Test
    public void getLocalTimeMachineTest() {
        double latitude = 42.3601;
        double longitude = -71.0589;
        long time = 265657600;
        String latLong = latitude + "," + longitude + "," + time;
        List<String> exclude = Arrays.asList("currently", "hourly");
        String extend = null;
        String lang = null;
        String units = null;
        Forecast response = api.getLocalForecast(key, latLong, exclude, extend, lang, units);

        Assert.assertEquals(BigDecimal.valueOf(latitude), response.getLatitude());
        Assert.assertEquals(BigDecimal.valueOf(longitude), response.getLongitude());
        Assert.assertNull(response.getCurrently());
        Assert.assertEquals(1, response.getDaily().getData().size());
    }

    @Configuration
    static class Config {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer();
        }

    }
}
