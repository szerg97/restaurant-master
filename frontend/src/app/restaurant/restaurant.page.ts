import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IonicModule } from '@ionic/angular';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.page.html',
  styleUrls: ['./restaurant.page.scss'],
  standalone: true,
  imports: [IonicModule],
})
export class RestaurantPage implements OnInit {
  public restaurant!: string;
  private activatedRoute = inject(ActivatedRoute);
  constructor() {}

  ngOnInit() {
    this.restaurant = this.activatedRoute.snapshot.paramMap.get('id') as string;
  }
}
