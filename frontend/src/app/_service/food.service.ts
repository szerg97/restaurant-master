import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Food } from '../_model/Food';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getFoods(offset: number, limit: number){
    return this.http.get<Food[]>(`${this.baseUrl}/foods?offset=${offset}&limit=${limit}`);
  }
}
