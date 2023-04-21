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
    return this.http.get<Food[]>(`${this.baseUrl}/foods${this.parsedUrl(offset, limit)}`);
  }

  private parsedUrl(offset: number, limit: number){
    if (offset != null && limit != null){
      return `?offset=${offset}&limit=${limit}`;
    }
    else if (offset != null && limit == null){
      return `?offset=${offset}`;
    }
    else if (offset == null && limit != null){
      return `?limit=${limit}`;
    }
    return '';
  }
}
