package com.anthonylldev.meteoesapi.municipality.application.mapper;

import com.anthonylldev.meteoesapi.municipality.application.dto.MunicipalityDto;
import com.anthonylldev.meteoesapi.municipality.domain.model.Municipality;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MunicipalityMapper {

    public MunicipalityDto toDto(Municipality municipality) {
        return new MunicipalityDto(municipality.code(), municipality.name());
    }

    public List<MunicipalityDto> toDto(List<Municipality> municipalities) {
        return municipalities.stream().map(this::toDto).toList();
    }
}
