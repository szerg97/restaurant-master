import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FoodPage } from './food.page';

describe('FoodPage', () => {
  let component: FoodPage;
  let fixture: ComponentFixture<FoodPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(FoodPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
