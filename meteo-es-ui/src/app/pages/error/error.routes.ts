import {Routes} from '@angular/router';

export const errorRoutes: Routes = [
  {
    path: 'not-found',
    loadComponent: () => import('./error').then(m => m.Error),
    title: 'Error 404',
    data: {
      title: 'Error 404',
      description: 'Page not found'
    }
  },
  {
    path: 'server-error',
    loadComponent: () => import('./error').then(m => m.Error),
    title: 'Error 500',
    data: {
      title: 'Error 500',
      description: 'Server error'
    }
  },
  {
    path: '**',
    redirectTo: 'not-found'
  }
];
