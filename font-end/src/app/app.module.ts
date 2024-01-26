import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { PopupComponent } from './component/popup/popup.component';
import { RegisterComponent } from './component/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ShopComponent,
    CartComponent,
    PayComponent,
    LoginComponent,
    RegisterComponent,
    AdminIndexComponent,
    AdminHomeComponent,
    AdminAddItemComponent,
    AdminEditItemComponent,
    AdminListItemComponent,
    ItemComponent,
    PopupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatDialogModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
