import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/service/cart.service';
import { FormatService } from 'src/app/service/format.service';
import { PopupService } from 'src/app/service/popup.service';

interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
  total: number;
  imageUrl: String;
}

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems: any;

  dataLoaded: boolean = false;

  constructor(
    private cartService: CartService,
    private formatService: FormatService,
    private router: Router,
    private popup: PopupService
  ) {}
  ngOnInit(): void {
    this.getItemCart();
  }

  getTotal(): number {
    let totalPrice = 0;

    this.cartItems.forEach((item: any) => {
      const itemPrice = item.item.price;
      const itemCount = item.count;
      if (item.selected) totalPrice += itemPrice * itemCount;
    });
    return totalPrice;
  }

  formatNumber(number: number): any {
    return this.formatService.formatNumber(number);
  }
  async getItemCart() {
    try {
      const res: any = await this.cartService.getItemCart().toPromise();
      this.cartItems = res.cart.map((cartItem: any) => ({
        ...cartItem,
        selected: true,
      }));
      this.dataLoaded = true;
    } catch (error) {
      console.log(error);
    }
  }

  async deleteItemCart(cartItem: any) {
    try {
      const res: any = await this.cartService
        .deleteItemCart(cartItem.item.id)
        .toPromise();
      if (res.status == 'success') {
        this.cartItems = this.cartItems.filter(
          (cart: any) => cart !== cartItem
        );
        this.popup.openPopup('sản phẩm', 'Đã xóa sản phẩm thành công');
      } else {
        this.popup.openPopup('sản phẩm', 'Xóa sản phẩm thất bại');
      }
    } catch (error) {
      console.log(error);
    }
  }

  async setCart(id: number, count: number) {
    try {
      console.log("id:"+id);
      console.log("cout:"+count);
      const res: any =await this.cartService
      .addItemToCart({ id: id, count:count}).toPromise()
      if (res.status !== 'success') {
        this.popup.openPopup('sản phẩm', 'chỉnh sửa số lượng sản phẩm thất bại');
      }
    } catch (error) {
      console.error(error);
    }
  }

  checkout() {
    const selectedItemsIds = this.cartItems
      .filter((cart: any) => cart.selected)
      .map((cart: any) => [cart.item.id, cart.count]);
    this.cartService.setItemSelected(selectedItemsIds);
    this.router.navigate(['/pay']);
  }

  async onInputChange(event: any, cart: any){
    const newValue = event.target.value;
    if (this.isValidInput(newValue)) {
      cart.count = parseInt(newValue, 10);
    }
    const res: any = await this.setCart(cart.item.id, cart.count);
  }

  async increment(cart: any) {
    cart.count++;
    const res: any = await this.setCart(cart.item.id, cart.count);
  }

  async decrement(cart: any){
    if (cart.count > 0) {
      cart.count--;
      const res: any = await this.setCart(cart.item.id, cart.count);
    }
  }

  private isValidInput(value: string): boolean {
    return !isNaN(parseInt(value, 10));
  }
}
