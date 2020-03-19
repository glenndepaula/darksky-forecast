package io.glenn.darksky.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, Date> {
    @Override
    public Date convert(OffsetDateTime offsetDateTime) {
        return Date.from(offsetDateTime.atZoneSameInstant(ZoneId.of("UTC")).toInstant());
    }
}
