package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.in.rest;

import com.anthonylldev.meteoesapi.municipality.application.port.in.SearchMunicipalitiesPort;
import com.anthonylldev.meteoesapi.municipality.application.dto.MunicipalityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/municipalities")
public class MunicipalityController {

    private final SearchMunicipalitiesPort municipalityPort;

    public MunicipalityController(SearchMunicipalitiesPort municipalityPort) {
        this.municipalityPort = municipalityPort;
    }

    @GetMapping
    public ResponseEntity<List<MunicipalityDto>> getMunicipalities(@RequestParam(required = false) String nameFilter) {
        return ResponseEntity.ok(municipalityPort.searchMunicipalities(nameFilter));
    }
}
