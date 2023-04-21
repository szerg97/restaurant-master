import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { HttpClientModule } from '@angular/common/http';
import { FoodService } from 'src/app/_service/food.service';
import { Food } from 'src/app/_model/Food';

@Component({
  selector: 'app-food',
  templateUrl: './food.page.html',
  styleUrls: ['./food.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule, HttpClientModule]
})
export class FoodPage implements OnInit {

  foods: Food[] = [];

  constructor(public foodService: FoodService) { }

  ngOnInit() {
    //this.getFoods();
  }

  onQuery(form: NgForm){
    if (!form.valid){
      return;
    }
    let offset = form.value.offset;
    const limit = form.value.limit;
    if (offset == null) {
      offset = 0;
    }
    this.foodService.getFoods(offset, limit).subscribe(result => {
      this.foods = result;
      console.log(result);
    }, error => {
      console.log(error);
    });
  }

  getFoods(){
    this.foodService.getFoods(50, 25).subscribe(result => {
      this.foods = result;
      console.log(result);
    }, error => {
      console.log(error);
    });
  }

}
