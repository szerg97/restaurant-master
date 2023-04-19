import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../_model/Order';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  placeOrder(order: any){
    return this.http.post<Order[]>(`${this.baseUrl}/order`, order);
  }
}
