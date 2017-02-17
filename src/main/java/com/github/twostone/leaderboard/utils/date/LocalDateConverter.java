package com.github.twostone.leaderboard.utils.date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<Instant, Date> {

    @Override
    public Date convertToDatabaseColumn(Instant date) {
        return Date.from(date);
    }

    @Override
    public Instant convertToEntityAttribute(Date value) {
        return value.toInstant();
    }
}