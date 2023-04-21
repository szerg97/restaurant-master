import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Menu } from 'src/app/_model/Menu';
import { MenuService } from 'src/app/_service/menu.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule, HttpClientModule]
})
export class MenuPage implements OnInit {

  menus: Menu[] = [];

  constructor(public menuService: MenuService) { }

  ngOnInit() {
    this.getMenus();
  }

  getMenus(){
    this.menuService.getMenus().subscribe(result => {
      this.menus = result;
      console.log(result);
    }, error => {
      console.log(error);
    });
  }

}
