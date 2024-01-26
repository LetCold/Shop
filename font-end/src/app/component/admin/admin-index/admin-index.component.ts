import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AccountService } from 'src/app/service/account.service';

@Component({
  selector: 'app-admin-index',
  templateUrl: './admin-index.component.html',
  styleUrls: ['./admin-index.component.css']
})
export class AdminIndexComponent {

  constructor(private account:AccountService, private cookie: CookieService){}

  logout(){
    this.account.logout();
  }
}
