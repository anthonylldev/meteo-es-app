import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemperatureUnitSelector } from './temperature-unit-selector';

describe('TemperatureUnitSelector', () => {
  let component: TemperatureUnitSelector;
  let fixture: ComponentFixture<TemperatureUnitSelector>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TemperatureUnitSelector]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TemperatureUnitSelector);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
