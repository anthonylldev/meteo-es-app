package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.mapper;

import com.anthonylldev.meteoesapi.municipality.domain.model.Municipality;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.dto.AemetMunicipalityDto;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.exception.InvalidMunicipalityIdException;
import org.springframework.stereotype.Component;

@Component
public class AemetMunicipalityMapper {

    public Municipality toMunicipality(AemetMunicipalityDto dto) {
        return new Municipality(extractMunicipalityCode(dto.id()), dto.nombre());
    }

    private String extractMunicipalityCode(String rawId) {
        if (rawId == null) {
            throw new InvalidMunicipalityIdException("null");
        }

        if (!rawId.matches("^id\\d{5}$")) {
            throw new InvalidMunicipalityIdException(rawId);
        }

        return rawId.substring(rawId.length() - 5);
    }
}
