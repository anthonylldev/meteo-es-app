import {Routes} from '@angular/router';

export const weatherRoutes: Routes = [
  {
    path: 'weather',
    title: 'Weather',
    loadComponent: () => import('./weather').then(m => m.Weather),
  }
];
