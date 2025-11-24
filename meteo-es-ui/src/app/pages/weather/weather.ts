import {Component} from '@angular/core';
import {Card} from 'primeng/card';
import {IMunicipality, MunicipalityNameFilter} from '@components/municipality';
import {TemperatureUnit, TemperatureUnitSelector} from '@components/temperature-unit-selector';
import {Forecast} from '@components/forecast';

@Component({
  selector: 'meteo-es-weather',
  imports: [
    Card,
    MunicipalityNameFilter,
    TemperatureUnitSelector,
    Forecast
  ],
  templateUrl: './weather.html',
  styleUrl: './weather.scss',
})
export class Weather {
  municipality?: IMunicipality = { code: '28079', name: 'Madrid' };
  temperatureUnit = TemperatureUnit.CELSIUS;
}
