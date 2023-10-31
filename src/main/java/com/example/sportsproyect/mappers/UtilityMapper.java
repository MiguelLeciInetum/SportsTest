package com.example.sportsproyect.mappers;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UtilityMapper {

    public LocalDate fromIntegerDateToLocalDate(Integer integerDate) {
        if (integerDate == null) {
            return null;
        }
        try {
            DateTimeFormatter integerDatePattern = DateTimeFormatter.ofPattern("yyyyMMdd");
            return LocalDate.parse(Integer.toString(integerDate), integerDatePattern);
        } catch (DateTimeParseException e) {
            log.warn("Failed to convert integer [{}] to a [yyyyMMdd] date. Returning null.", integerDate);
            return null;
        }
    }
}