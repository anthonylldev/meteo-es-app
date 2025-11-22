import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {MainLayout} from '@layout/main-layout';

@Component({
  selector: 'meteo-es-root',
  imports: [
    RouterOutlet,
    MainLayout
  ],
  template: `
    <meteo-es-main-layout>
      <router-outlet/>
    </meteo-es-main-layout>
  `
})
export class App {
}
