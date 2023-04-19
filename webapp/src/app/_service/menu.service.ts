import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment/environment';
import { Menu } from '../_model/Menu';
import { BehaviorSubject, map, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  baseUrl = environment.apiUrl;

  private _menus = new BehaviorSubject<Menu[]>([]);

  get menus() {
    return this._menus.asObservable();
  }

  constructor(private http: HttpClient) {
  }

  getMenus(){
    return this.http.get<Menu[]>(`${this.baseUrl}/menu`)
      .pipe(
        map(response => {
          const menus: Menu[] = [];
          for(const data in response){
            menus.push(response[data]);
          }
          console.log(menus);
          return menus;
        }),
        tap(menus => {
          this._menus.next(menus);
        })
      );
  }

  getMyMenus(){
    return this.http.get<Menu[]>(`${this.baseUrl}/menu`);
  }
}
