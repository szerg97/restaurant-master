import { Component, OnDestroy, OnInit } from '@angular/core';
import { MenuService } from '../_service/menu.service';
import { Menu } from '../_model/Menu';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit, OnDestroy{

  menus: Menu[] = [];
  myMenus: Menu[] = [];
  private menusSub: Subscription = new Subscription;

  constructor(public menuService: MenuService){

  }
  
  ngOnInit(): void {
    this.getMyMenus();
    this.menuService.getMenus();
    this.menusSub = this.menuService.menus.subscribe(menus => {
      this.menus = menus;
    });
  }

  getMyMenus(){
    this.menuService.getMyMenus().subscribe(result => {
      this.myMenus = result;
      console.log(result);
    }, error => {
      console.log(error);
    });
  }

  ngOnDestroy() {
    if (this.menusSub) {
      this.menusSub.unsubscribe();
    }
  }
  
}
