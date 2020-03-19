package io.glenn.darksky.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Location implements Comparable {

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
    public int compareTo(Object other) {
        if(other instanceof Location) {
            Location otherLocation = (Location) other;
            if(otherLocation.getLatitude().equals(this.latitude))
                return otherLocation.getLongitude().compareTo(this.longitude);
            else return otherLocation.getLatitude().compareTo(this.latitude);
        } else return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return String.format(
            "Location[name=%s, latitude=%d, longitude='%d']",
            name, latitude, longitude);
    }
}
