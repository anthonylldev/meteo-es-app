package com.anthonylldev.meteoesapi.municipality.application.port.in;

import com.anthonylldev.meteoesapi.municipality.application.dto.MunicipalityDto;

import java.util.List;

public interface SearchMunicipalitiesPort {
    List<MunicipalityDto> searchMunicipalities(String nameFilter);
}
