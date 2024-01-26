import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';


const TOKEN_KEY = 'token';


@Injectable({
  providedIn: 'root'
})

export class StorageService {

  constructor(private cookie: CookieService) { }

  clean(): boolean {
    this.cookie.deleteAll();
    return !this.isLoggedIn();
  }
  saveToken(token: any): boolean {
    this.cookie.set(TOKEN_KEY, token);
    return this.isLoggedIn();
  }
  getToken(): any {
    return this.cookie.get(TOKEN_KEY);
  }
  isLoggedIn(): boolean {
    return !!this.cookie.get(TOKEN_KEY);
  }

  isAdmin(){
    return this.getRole() == "ADMIN" ? true : false;
  }

  getFirstName():any{
    const data = this.decodeToken(this.getToken());
    return data.firstName;
  }

  getLastName(): any{
    const data = this.decodeToken(this.getToken());
    return data.lastName;
  }

  getRole():any{
    const data = this.decodeToken(this.getToken());
    return data.role;
  }

  decodeToken(token: any) :any{
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const decodedPayload = atob(base64);
    const payload = JSON.parse(decodeURIComponent(Array.from(decodedPayload).map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join('')));
    return payload;
  }
}
