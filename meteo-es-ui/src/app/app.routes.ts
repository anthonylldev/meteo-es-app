import {Routes} from '@angular/router';
import {weatherRoutes} from '@pages/weather';
import {errorRoutes} from '@pages/error';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'weather',
    pathMatch: 'full'
  },
  ...weatherRoutes,
  ...errorRoutes
];
