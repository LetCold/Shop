import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileService } from 'src/app/service/file.service';
import { ItemService } from 'src/app/service/item.service';
import { PopupService } from 'src/app/service/popup.service';

@Component({
  selector: 'app-admin-edit-item',
  templateUrl: './admin-edit-item.component.html',
  styleUrls: ['./admin-edit-item.component.css'],
})
export class AdminEditItemComponent implements OnInit {
  id: any = 0;

  DataForm: any = {
    name: null,
    gender: 0,
    type: 2,
    description: null,
    price: 0,
    image: null,
  };

  image: any;

  typeItems: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private itemService: ItemService,
    private file: FileService,
    private popup: PopupService
  ) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.getTypeItem();
    this.getItem();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.DataForm.image = file.name;
      this.image = file;
    }
  }

  async editItem() {
    try {
      const res: any = await this.itemService
        .editItem(this.id, this.DataForm)
        .toPromise();
      if (this.image != null) {
        try {
          const res: any = await this.file.uploadFile(this.image).toPromise();
        } catch (error) {
          console.log(error);
          this.popup.openPopup('Sản Phẩm', 'sửa sản phẩm thất bại');
        }
      }
      this.popup.openPopup('Sản Phẩm', 'Đã sửa sản phẩm thành công');
    } catch (error) {
      console.log(error);
      this.popup.openPopup('Sản Phẩm', 'sửa sản phẩm thất bại');
    }
  }

  async getTypeItem() {
    try {
      const res: any = await this.itemService.getTypes().toPromise();
      this.typeItems = res.typeItems;
    } catch (error) {
      console.log(error);
    }
  }

  async getItem() {
    try {
      const res: any = await this.itemService.getItem(this.id).toPromise();
      if (res == null) {
        this.router.navigate(['/admin/list-item']);
      }
      this.DataForm.name = res.name;
      this.DataForm.gender = res.gender;
      this.DataForm.type = res.type.id;
      this.DataForm.description = res.description;
      this.DataForm.price = res.price;
    } catch (error) {
      console.log(error);
    }
  }
}
