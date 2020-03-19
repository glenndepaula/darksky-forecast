package io.glenn.darksky.data;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Weather {

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private String timezone;

    @Column
    private String summary;

    @Column
    private OffsetDateTime time;

    @Column
    private BigDecimal temperatureHigh;

    @Column
    private BigDecimal temperatureLow;

    @Column
    private OffsetDateTime sunriseTime;

    @Column
    private OffsetDateTime sunsetTime;

    @OneToMany(mappedBy = "weather")
    private Set<HourlyForecast> hourlyForecasts;

    public Weather() { }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public BigDecimal getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(BigDecimal temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public BigDecimal getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(BigDecimal temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public OffsetDateTime getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(OffsetDateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public OffsetDateTime getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(OffsetDateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Set<HourlyForecast> getHourlyForecasts() {
        return hourlyForecasts;
    }

    public void setHourlyForecasts(Set<HourlyForecast> hourlyForecasts) {
        this.hourlyForecasts = hourlyForecasts;
    }

    public void addHourlyForecast(HourlyForecast hourlyForecast) {
        if(this.hourlyForecasts == null)
            this.hourlyForecasts = new HashSet<>();
        this.hourlyForecasts.add(hourlyForecast);
    }

    @Override
    public String toString() {
        return String.format(
            "Weather[location=%s, summary=%s]",
            location.toString(), summary);
    }
}
