import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@env/environment';
import {TemperatureUnit} from '@components/temperature-unit-selector';
import {IForecast} from '@components/forecast';

@Injectable({
  providedIn: 'root',
})
export class ForecastService {
  private readonly httpclient = inject(HttpClient);
  private readonly resourceUrl = environment.apiUrl + 'forecasts';

  get(
    municipalityCode: string,
    temperatureUnit: TemperatureUnit
  ) {
    return this.httpclient.get<IForecast>(`${this.resourceUrl}`, {
      params: {
        municipalityCode,
        temperatureUnit
      }
    });
  }
}
