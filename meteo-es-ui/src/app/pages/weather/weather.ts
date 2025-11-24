import {Component} from '@angular/core';
import {Card} from 'primeng/card';
import {IMunicipality, MunicipalityNameFilter} from '@components/municipality';
import {TemperatureUnit, TemperatureUnitSelector} from '@components/temperature-unit-selector';

@Component({
  selector: 'meteo-es-weather',
  imports: [
    Card,
    MunicipalityNameFilter,
    TemperatureUnitSelector
  ],
  templateUrl: './weather.html',
  styleUrl: './weather.scss',
})
export class Weather {
  municipality?: IMunicipality;
  temperatureUnit = TemperatureUnit.CELSIUS;
}
