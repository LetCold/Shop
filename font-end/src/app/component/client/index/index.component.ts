import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/service/account.service';
import { SearchService } from 'src/app/service/search.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {

  searchKeyword:string = "";

  constructor(
    private account: AccountService,
    private storage: StorageService,
    private searchService: SearchService,
    private router:Router
  ) { }

  logout() {
    this.account.logout();
  }

  isLogin() {
    return this.storage.isLoggedIn()
  }

  submitSearch() {
    this.searchService.updateSearchKeyword(this.searchKeyword);
  }
}
