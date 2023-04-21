import { Injectable } from '@angular/core';
import { Menu } from '../_model/Menu';
import {environment} from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getMenus(){
    return this.http.get<Menu[]>(`${this.baseUrl}/menu`);
  }
}
