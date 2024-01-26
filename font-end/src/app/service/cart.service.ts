import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private selectedItemsIds: number[] = [];

  private totalPrice: number=0;

  constructor(private api:ApiService) { }

  addItemToCart(data:any):any{
    return this.api.postApi("/client/cart", data);
  }

  getItemCart():any{
    return this.api.getApi("/client/cart");
  }

  deleteItemCart(id:any):any{
    return this.api.deleteApi("/client/cart/"+id);
  }

  setItemSelected(data: number[]){
    this.selectedItemsIds =data;
  }

  getItemSelected():any{
    return this.selectedItemsIds;
  }

  setTotelPrice(data:number):any{
    this.totalPrice = data;
  }

  getTotelPrice():any{
    return this.totalPrice;
  }

  buy(data:any):any{
    return this.api.postApi("/client/buy", data);
  }

}
