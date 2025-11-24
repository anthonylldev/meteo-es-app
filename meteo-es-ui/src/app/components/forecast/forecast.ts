import {Component, effect, inject, input, signal} from '@angular/core';
import {IMunicipality} from "@components/municipality";
import {TemperatureUnit, TemperatureUnitPipe} from "@components/temperature-unit-selector";
import {ForecastService} from '@components/forecast/services';
import {IForecast} from '@components/forecast/model';
import {finalize, Subscription} from 'rxjs';
import {DatePipe, DecimalPipe} from '@angular/common';
import {TableModule} from 'primeng/table';

@Component({
  selector: 'meteo-es-forecast',
  imports: [
    TemperatureUnitPipe,
    DatePipe,
    DecimalPipe,
    TableModule
  ],
  templateUrl: './forecast.html',
  styleUrl: './forecast.scss',
})
export class Forecast {
  municipality = input.required<IMunicipality>();
  temperatureUnit = input.required<TemperatureUnit>();
  forecast = signal<IForecast | undefined>(undefined)

  protected isLoading = false;

  private getSubscription?: Subscription;
  private readonly forecastService = inject(ForecastService);

  constructor() {
    effect(() => {
      this.isLoading = true;
      this.getSubscription?.unsubscribe();

      const municipalityCode = this.municipality().code;
      const temperatureUnit = this.temperatureUnit();

      this.getSubscription = this.forecastService
        .get(municipalityCode, temperatureUnit)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe(res => {
          this.forecast.set(res);
        })
    });
  }
}
