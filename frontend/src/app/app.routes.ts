import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'restaurant/home',
    pathMatch: 'full',
  },
  {
    path: 'restaurant/home',
    loadComponent: () =>
      import('./restaurant/restaurant.page').then((m) => m.RestaurantPage),
  },
  {
    path: 'restaurant/menu',
    loadComponent: () => import('./restaurant/menu/menu.page').then( m => m.MenuPage)
  },
  {
    path: 'restaurant/order',
    loadComponent: () => import('./restaurant/order/order.page').then( m => m.OrderPage)
  },
  {
    path: 'restaurant/food',
    loadComponent: () => import('./restaurant/food/food.page').then( m => m.FoodPage)
  },
  {
    path: 'restaurant/drink',
    loadComponent: () => import('./restaurant/drink/drink.page').then( m => m.DrinkPage)
  },
];
