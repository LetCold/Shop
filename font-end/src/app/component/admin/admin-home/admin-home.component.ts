import { Component, OnInit } from '@angular/core';
import { FormatService } from 'src/app/service/format.service';
import { ItemService } from 'src/app/service/item.service';
import { PopupService } from 'src/app/service/popup.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css'],
})
export class AdminHomeComponent implements OnInit {
  items: any;

  amount: number = 0;

  count: number = 0;

  constructor(
    private itemService: ItemService,
    private formatService: FormatService
  ) {}

  ngOnInit(): void {
    this.getAllItem();
  }
  async getAllItem() {
    try {
      const res: any = await this.itemService.getAdminHomePage().toPromise();
      this.items = res.items;
      this.amount = res.amount;
      this.count = res.count;
    } catch (error) {
      console.log(error);
    }
  }

  getMonth() {
    const currentDate = new Date();
    const currentMonth = currentDate.getMonth() + 1;
    return currentMonth
  }

  format(price: number): any {
    return this.formatService.formatNumber(price);
  }
}
