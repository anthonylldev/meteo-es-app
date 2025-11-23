import {Component, input, output} from '@angular/core';
import {TemperatureUnit, TemperatureUnitPipe} from '@components/temperature-unit-selector';
import {FormsModule} from '@angular/forms';
import {Menu} from 'primeng/menu';
import {Button} from 'primeng/button';
import {MenuItem} from 'primeng/api';
import {Ripple} from 'primeng/ripple';

@Component({
  selector: 'meteo-es-temperature-unit-selector',
  imports: [
    FormsModule,
    Menu,
    Button,
    TemperatureUnitPipe,
    Ripple
  ],
  templateUrl: './temperature-unit-selector.html',
  styleUrl: './temperature-unit-selector.scss',
})
export class TemperatureUnitSelector {
  temperatureUnit = input.required<TemperatureUnit>();
  temperatureUnitChange = output<TemperatureUnit>();

  items: MenuItem[] = [
    {
      label: TemperatureUnit.CELSIUS,
      value: TemperatureUnit.CELSIUS,
      command: () => this.temperatureUnitChange.emit(TemperatureUnit.CELSIUS)
    },
    {
      label: TemperatureUnit.FAHRENHEIT,
      value: TemperatureUnit.FAHRENHEIT,
      command: () => this.temperatureUnitChange.emit(TemperatureUnit.FAHRENHEIT)
    }
  ]
}
