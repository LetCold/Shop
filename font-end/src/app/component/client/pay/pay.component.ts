import { CartService } from 'src/app/service/cart.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopupService } from 'src/app/service/popup.service';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css'],
})
export class PayComponent implements OnInit {
  buyForm: any = {
    location: '',
    numberPhone: '',
    note: '',
    amount: 0,
    ids: [],
  };

  constructor(
    private cartService: CartService,
    private popup: PopupService,
    private router: Router
  ) {}
  ngOnInit(): void {
    if (this.cartService.getItemSelected().length == 0) {
      this.router.navigate(['/']);
      return;
    }
  }

  async paymet() {
    try {
      this.buyForm.ids = this.cartService.getItemSelected();
      this.buyForm.amount = this.cartService.getTotelPrice();
      const res: any = await this.cartService.buy(this.buyForm).toPromise();
      if (res.status == 'success') {
        this.cartService.setItemSelected([]);
        this.cartService.setTotelPrice(0);
        this.popup.openPopup('Buy', 'Mua thành công');
        this.router.navigate(['/']);
      } else {
        this.popup.openPopup('Buy', 'Mua Thất bại, vui lòng thử lại sau');
      }
    } catch (error) {
      console.log(error);
      this.popup.openPopup('Buy', 'Mua Thất bại, vui lòng thử lại sau');
    }
  }
}
