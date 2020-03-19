package io.glenn.darksky.data;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Location {

    @Column
    private String name;

    @Column
    private BigDecimal latitude;

    @Column
    private BigDecimal longitude;

    public Location() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format(
            "Location[name=%s, latitude=%d, longitude='%d']",
            name, latitude, longitude);
    }
}
