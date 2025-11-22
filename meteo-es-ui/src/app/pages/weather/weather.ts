import {Component} from '@angular/core';
import {Card} from 'primeng/card';
import {IMunicipality, MunicipalityNameFilter} from '@components/municipality';

@Component({
  selector: 'meteo-es-weather',
  imports: [
    Card,
    MunicipalityNameFilter
  ],
  templateUrl: './weather.html',
  styleUrl: './weather.scss',
})
export class Weather {
  municipality?: IMunicipality;
}
