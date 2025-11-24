import {Pipe, PipeTransform} from '@angular/core';
import {TemperatureUnit} from '@components/temperature-unit-selector';

@Pipe({
  name: 'temperatureUnit'
})
export class TemperatureUnitPipe implements PipeTransform {
  transform(value: TemperatureUnit | string): string {
    if (value === TemperatureUnit.CELSIUS) {
      return '°C';
    }

    if (value === TemperatureUnit.FAHRENHEIT) {
      return '°F';
    }

    return value;
  }
}
