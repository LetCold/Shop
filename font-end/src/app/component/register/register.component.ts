import { Component } from '@angular/core';
import { AccountService } from 'src/app/service/account.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  regiserForm: any = {
    firstName:"",
    lastName: "",
    gender: 0,
    email: "",
    password: "",
    rePassword:""
  };

  constructor(private account:AccountService){}

  togglePassword(fieldName: string) {
    const inputField = document.getElementById(fieldName) as HTMLInputElement;
    const fieldType = inputField.type;
    if (fieldType === 'password') {
      inputField.type = 'text';
    } else {
      inputField.type = 'password';
    }
  }

  onSubmit() {
    this.account.register(this.regiserForm)
  }
}
