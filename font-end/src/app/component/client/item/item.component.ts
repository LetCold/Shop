import { ItemService } from 'src/app/service/item.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormatService } from 'src/app/service/format.service';
import { PopupService } from 'src/app/service/popup.service';
import { StorageService } from 'src/app/service/storage.service';
import { CartService } from '../../../service/cart.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css'],
})
export class ItemComponent implements OnInit {
  item: any = {
    name: '',
    description: '',
    type: {
      id: 0,
      name: '',
    },
    gender: 0,
    price: 0,
  };

  idItem: any = 0;

  load: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private itemService: ItemService,
    private cartService: CartService,
    private formatService: FormatService,
    private storage: StorageService,
    private popup: PopupService
  ) {}
  ngOnInit(): void {
    this.idItem = this.activatedRoute.snapshot.paramMap.get('id');
    this.getItem();
  }

  async getItem() {
    try {
      const res: any = await this.itemService.getItem(this.idItem).toPromise();
      if (res == null) {
        this.router.navigate(['/']);
      }
      this.item = res;
      this.load = true;
    } catch (error) {
      console.log(error);
    }
  }

  format(price: number): any {
    return this.formatService.formatNumber(price);
  }

  async addItemToCart() {
    try {
      if (!this.storage.isLoggedIn()) {
        this.router.navigate(['/login']);
      } else {
        const res: any = await this.cartService
          .addItemToCart({ id: this.idItem, count:1 })
          .toPromise();
        this.popup.openPopup('Giỏ Hàng', 'Đã thêm sản phẩm vào giỏ hàng');
      }
    } catch (error) {
      this.popup.openPopup('Giỏ Hàng', 'Thêm sản phẩm vào giỏ hàng Thất bại');
      console.log(error);
    }
  }
}
