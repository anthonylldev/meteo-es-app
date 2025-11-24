package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet;

import com.anthonylldev.meteoesapi.infrastructure.aemet.AemetWebClient;
import com.anthonylldev.meteoesapi.infrastructure.aemet.dto.AemetMetadataDto;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidDataException;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidMetadataException;
import com.anthonylldev.meteoesapi.municipality.application.port.out.MunicipalityGateway;
import com.anthonylldev.meteoesapi.municipality.domain.model.Municipality;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.dto.AemetMunicipalityDto;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.mapper.AemetMunicipalityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AemetMunicipalityAdapter implements MunicipalityGateway {

    private final AemetWebClient aemetWebClient;
    private final AemetMunicipalityMapper mapper;

    public AemetMunicipalityAdapter(
            AemetWebClient aemetWebClient,
            AemetMunicipalityMapper mapper
    ) {
        this.aemetWebClient = aemetWebClient;
        this.mapper = mapper;
    }

    @Override
    public List<Municipality> getMunicipalities() {
        log.info("Requesting municipality metadata from AEMET...");

        AemetMetadataDto metadata = aemetWebClient.getMunicipalityMetadata();

        if (metadata == null || metadata.datos() == null) {
            throw new AemetInvalidMetadataException();
        }

        log.info("Fetching municipality list from: {}", metadata.datos());

        List<AemetMunicipalityDto> aemetMunicipalities = aemetWebClient.getMunicipalityList(metadata.datos());

        if (aemetMunicipalities == null) {
            throw new AemetInvalidDataException();
        }

        log.info("Successfully retrieved {} municipalities.", aemetMunicipalities.size());

        return aemetMunicipalities.stream()
                .map(mapper::toMunicipality)
                .toList();
    }
}
