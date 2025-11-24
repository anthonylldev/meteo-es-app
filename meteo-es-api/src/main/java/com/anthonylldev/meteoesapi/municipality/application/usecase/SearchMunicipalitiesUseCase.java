package com.anthonylldev.meteoesapi.municipality.application.usecase;

import com.anthonylldev.meteoesapi.municipality.application.dto.MunicipalityDto;
import com.anthonylldev.meteoesapi.municipality.application.mapper.MunicipalityMapper;
import com.anthonylldev.meteoesapi.municipality.application.port.in.SearchMunicipalitiesPort;
import com.anthonylldev.meteoesapi.municipality.application.port.out.MunicipalityGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchMunicipalitiesUseCase implements SearchMunicipalitiesPort {

    private final MunicipalityGateway municipalityGateway;
    private final MunicipalityMapper mapper;

    public SearchMunicipalitiesUseCase(MunicipalityGateway municipalityGateway, MunicipalityMapper mapper) {
        this.municipalityGateway = municipalityGateway;
        this.mapper = mapper;
    }

    @Override
    public List<MunicipalityDto> searchMunicipalities(String nameFilter) {
        List<MunicipalityDto> municipalities = mapper.toDto(municipalityGateway.getMunicipalities());

        if (nameFilter == null || nameFilter.trim().isEmpty()) {
            return municipalities;
        }

        return municipalities.stream().filter(m -> m.name().toLowerCase().contains(nameFilter.toLowerCase())).toList();
    }
}
