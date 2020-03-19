package io.glenn.darksky.data;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class HourlyForecast {

    @Column
    private String summary;

    @Column
    private OffsetDateTime time;

    @Column
    private String timezone;

    @Column
    private BigDecimal temperature;

    @ManyToOne
    @JoinColumn(name = "weather_id")
    private Weather weather;

    public HourlyForecast() { }

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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return String.format(
                "HourlyForecast[location=%s, time=%s, temperature=%d]",
                weather.getLocation().toString(), time.toString(), temperature);
    }
}
