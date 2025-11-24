import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MunicipalityNameFilter } from './municipality-name-filter';

describe('MunicipalityNameFilter', () => {
  let component: MunicipalityNameFilter;
  let fixture: ComponentFixture<MunicipalityNameFilter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MunicipalityNameFilter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MunicipalityNameFilter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
