package com.example.sportsproyect.mappers;

import com.example.sportsproyect.mappers.UtilityMapper;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = UtilityMapper.class)
public interface SportsMapperConfig {
}