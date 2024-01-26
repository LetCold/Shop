import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './api.service';
import { PopupService } from './popup.service';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor(
    private api: ApiService,
    private storage: StorageService,
    private router: Router,
    private popup: PopupService
  ) {}

  async register(data: any) {
    try {
      const res: any = this.api.postApi('/auth/register', data).toPromise();
      if (res.token == null) {
        this.popup.openPopup('Đăng kí Thất Bại', 'vui lòng thử lại sau');
      } else {
        const callBack = this.storage.saveToken(res.token);
        if (callBack) {
          this.router.navigate(['/']);
        } else {
          this.popup.openPopup('Đăng nhập Thất Bại', 'vui lòng thử lại sau');
        }
        this.router.navigate(['/']);
      }
    } catch (error) {
      console.log(error);
    }
  }

  async login(data: any) {
    try {
      const res: any = await this.api.postApi('/auth/login', data).toPromise();
      if (res.token != null) {
        const callBack = this.storage.saveToken(res.token);
        if (callBack) {
          this.router.navigate(['/']);
        } else {
          this.popup.openPopup('Đăng nhập Thất Bại', 'vui lòng thử lại sau');
        }
        this.moveByRole();
      } else {
        this.popup.openPopup('Đăng nhập Thất Bại', 'vui lòng thử lại sau');
      }
    } catch (error) {
      console.log(error);
    }
  }

  async logout() {
    try {
      const res:any = await this.api.getApi("/auth/logout").toPromise();
      if (res.status == 'success') {
        const callBack = this.storage.clean();
        if (callBack) this.router.navigate(['/']);
      }
    } catch (error) {
      console.log(error);
    }
  }

  moveByRole() {
    if (this.storage.getRole() == 'ADMIN') {
      this.router.navigate(['/admin']);
    } else {
      this.router.navigate(['/']);
    }
  }
}
