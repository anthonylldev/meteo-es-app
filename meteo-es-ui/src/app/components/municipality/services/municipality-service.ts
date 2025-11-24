import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@env/environment';
import {IMunicipality} from '@components/municipality';

@Injectable({
  providedIn: 'root',
})
export class MunicipalityService {
  private readonly httpclient = inject(HttpClient);
  private readonly resourceUrl = environment.apiUrl + 'municipalities';

  search(nameFilter?: string) {
    return this.httpclient.get<IMunicipality[]>(`${this.resourceUrl}`, {
      params: nameFilter && nameFilter !== '' ? {nameFilter} : {}
    });
  }
}
