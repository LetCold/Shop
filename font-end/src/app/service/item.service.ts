import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { SearchService } from './search.service';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  constructor(private api: ApiService, private earchService: SearchService) {}

  getAllItem(name: any): any {
    if (name == '' || name == null) {
      return this.api.getApi('/client/shop');
    } else {
      return this.api.getApi('/client/shop/category/' + name);
    }
  }

  getAllItemSearch(data: any): any {
    return this.api.postApi('/client/shop/search', data);
  }

  addItem(data: any): any {
    return this.api.postApi('/admin/item', data);
  }

  getTypes(): any{
    return this.api.getApi('/admin/item/types');
  }

  getItem(id: any): any {
    return this.api.getApi('/client/shop/' + id);
  }

  editItem(id: number, data: any): any {
    return this.api.putApi('/admin/item/' + id, data);
  }

  delete(id: any): any {
    return this.api.deleteApi('/admin/item/' + id);
  }

  getAdminHomePage(): any {
    return this.api.getApi("/admin/item/home");
  }
}
