import { Component, OnInit } from '@angular/core';
import { FormatService } from 'src/app/service/format.service';
import { ItemService } from 'src/app/service/item.service';
import { PopupService } from 'src/app/service/popup.service';

@Component({
  selector: 'app-admin-list-item',
  templateUrl: './admin-list-item.component.html',
  styleUrls: ['./admin-list-item.component.css'],
})
export class AdminListItemComponent implements OnInit {
  items: any;

  constructor(
    private itemService: ItemService,
    private formatService: FormatService,
    private popup: PopupService
  ) {}

  ngOnInit(): void {
    this.getAllItem();
  }
  async getAllItem() {
    try {
      const res: any = await this.itemService.getAllItem('').toPromise();
      this.items = res.item;
    } catch (error) {
      console.log(error);
    }
  }

  async deleteItem(idItem: number) {
    try {
      const res: any = await this.itemService.delete(idItem).toPromise();
      this.popup.openPopup('Sản Phẩm', 'Đã xóa sản phẩm thành công');
    } catch (error) {
      console.log(error);
      this.popup.openPopup('Sản Phẩm', 'Xóa sản phẩm Thất Bại');
    }
  }

  format(price: number): any {
    return this.formatService.formatNumber(price);
  }
}
