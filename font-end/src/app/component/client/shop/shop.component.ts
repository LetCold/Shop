import { Component, OnInit } from '@angular/core';
import { FormatService } from 'src/app/service/format.service';
import { ItemService } from 'src/app/service/item.service';
import { SearchService } from '../../../service/search.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})
export class ShopComponent implements OnInit {
  searchKeyword = '';

  items: any;

  data: any = '';

  dataLoaded: boolean = false;

  type: any;

  constructor(
    private itemService: ItemService,
    private formatService: FormatService,
    private searchService: SearchService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.type = this.activatedRoute.snapshot.paramMap.get('id');
    if (!this.type) {
      this.type = 0;
    }
    this.loadItem();
    this.searchService.searchKeyword$.subscribe((keyword) => {
      this.searchKeyword = keyword;
      this.loadItemSearch(keyword);
    });
  }

  setType(data: number) {
    this.searchService.setFilter(data);

    this.loadItemSearch(this.searchKeyword);
  }

  async loadItem() {
    try {
      const res: any = await this.itemService.getAllItem(this.type).toPromise();
      this.items = res.item;
      this.dataLoaded = true;
    } catch (error) {
      console.log(error);
    }
  }

  async loadItemSearch(data: String) {
    try {
      const res: any = await this.itemService
        .getAllItemSearch({
          search: data,
          filter: this.searchService.getFilter(),
          type: this.type,
        })
        .toPromise();
      this.items = res.item;
      this.dataLoaded = true;
    } catch (error) {
      console.log(error);
    }
  }

  format(price: number): any {
    return this.formatService.formatNumber(price);
  }
}
