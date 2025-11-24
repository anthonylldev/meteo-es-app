import {Component, inject, input, output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AutoComplete, AutoCompleteCompleteEvent} from 'primeng/autocomplete';
import {Subscription} from 'rxjs';
import {IMunicipality, MunicipalityService} from '@components/municipality';

@Component({
  selector: 'meteo-es-municipality-name-filter',
  imports: [
    FormsModule, AutoComplete
  ],
  templateUrl: './municipality-name-filter.html',
  styleUrl: './municipality-name-filter.scss',
})
export class MunicipalityNameFilter {
  municipality = input<IMunicipality>()
  municipalityChange = output<IMunicipality>()

  protected items: IMunicipality[] = [];

  private searchSubscription?: Subscription;
  private readonly municipalityService = inject(MunicipalityService)

  search(event: AutoCompleteCompleteEvent): void {
    this.searchSubscription?.unsubscribe();
    this.searchSubscription = this.municipalityService
      .search(event.query)
      .subscribe(municipalities => this.items = municipalities);
  }
}
