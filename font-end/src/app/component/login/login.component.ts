import { Component } from '@angular/core';
import { AccountService } from 'src/app/service/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: any = {
    email: '',
    password: '',
  };

  constructor(private account: AccountService) {}

  onSubmit() {
    this.account.login(this.loginForm);

  }
}
