import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DrinkPage } from './drink.page';

describe('DrinkPage', () => {
  let component: DrinkPage;
  let fixture: ComponentFixture<DrinkPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(DrinkPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
