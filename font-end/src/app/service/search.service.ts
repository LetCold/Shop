import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  filter = 0;

  private searchKeywordSource = new Subject<string>();

  searchKeyword$ = this.searchKeywordSource.asObservable();

  updateSearchKeyword(keyword: string): void {
    this.searchKeywordSource.next(keyword);
  }

  setFilter(data:number){
    this.filter = data;
  }

  getFilter():number{
    return this.filter;
  }
}
