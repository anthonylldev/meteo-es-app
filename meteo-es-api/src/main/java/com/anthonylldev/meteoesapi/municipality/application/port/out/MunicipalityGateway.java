package com.anthonylldev.meteoesapi.municipality.application.port.out;

import com.anthonylldev.meteoesapi.municipality.domain.model.Municipality;

import java.util.List;

public interface MunicipalityGateway {
    List<Municipality> getMunicipalities();
}
