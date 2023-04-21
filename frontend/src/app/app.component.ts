import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { IonicModule } from '@ionic/angular';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
  standalone: true,
  imports: [IonicModule, RouterLink, RouterLinkActive, CommonModule],
})
export class AppComponent {
  public appPages = [
    { title: 'Home', url: '/restaurant/home', icon: 'home' },
    { title: 'Order', url: '/restaurant/order', icon: 'card' },
    { title: 'Menu', url: '/restaurant/menu', icon: 'newspaper' },
    { title: 'Food', url: '/restaurant/food', icon: 'pizza' },
    { title: 'Drink', url: '/restaurant/drink', icon: 'beer' }
  ];
  public labels = ['Family', 'Friends', 'Notes', 'Work', 'Travel', 'Reminders'];
  constructor() {}
}
