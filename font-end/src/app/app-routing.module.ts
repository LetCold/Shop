import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminAddItemComponent } from './component/admin/admin-add-item/admin-add-item.component';
import { AdminEditItemComponent } from './component/admin/admin-edit-item/admin-edit-item.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminIndexComponent } from './component/admin/admin-index/admin-index.component';
import { AdminListItemComponent } from './component/admin/admin-list-item/admin-list-item.component';
import { CartComponent } from './component/client/cart/cart.component';
import { IndexComponent } from './component/client/index/index.component';
import { ItemComponent } from './component/client/item/item.component';
import { PayComponent } from './component/client/pay/pay.component';
import { ShopComponent } from './component/client/shop/shop.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { GuardService } from './service/guard.service';

const routes: Routes = [
  {
    path: "",
    component: IndexComponent,
    children: [
      {
        path: "", component: ShopComponent
      },
      {
        path: "shop/:id", component: ShopComponent
      },
      {
        path: "cart", component: CartComponent, canActivate: [() => (inject(GuardService).checkUser())]
      },
      {
        path: "pay", component: PayComponent, canActivate: [() => (inject(GuardService).checkUser())]
      },
      {
        path: "item/:id", component: ItemComponent
      },
      {
        path: "login", component: LoginComponent, canActivate: [() => inject(GuardService).checkAuth()]
      },
      {
        path: "register", component: RegisterComponent, canActivate: [() => inject(GuardService).checkAuth()]
      },

    ]
  },
  {
    path: "admin",
    component: AdminIndexComponent, canActivate: [() => inject(GuardService).checkAdmin()],
    children: [
      {
        path: "", component: AdminHomeComponent
      },
      {
        path: "add-item", component: AdminAddItemComponent
      },
      {
        path: "edit-item/:id", component: AdminEditItemComponent
      },
      {
        path: "list-item", component: AdminListItemComponent
      }
    ]
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
