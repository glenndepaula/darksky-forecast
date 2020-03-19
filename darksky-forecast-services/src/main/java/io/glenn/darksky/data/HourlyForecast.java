package io.glenn.darksky.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class HourlyForecast implements Comparable {

    @Column
    private String summary;

    @Column
    private OffsetDateTime time;

    @Column
    private String timezone;

    @Column
    private BigDecimal temperature;

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

    @Override
    public int compareTo(Object other) {
        if(other instanceof HourlyForecast)
            return this.time.compareTo(((HourlyForecast) other).getTime());
        else return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return String.format(
                "HourlyForecast[time=%s, temperature=%d]",
                time.toString(), temperature);
    }
}
