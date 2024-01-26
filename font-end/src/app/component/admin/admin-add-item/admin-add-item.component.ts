import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/service/file.service';
import { PopupService } from 'src/app/service/popup.service';
import { ItemService } from '../../../service/item.service';

@Component({
  selector: 'app-admin-add-item',
  templateUrl: './admin-add-item.component.html',
  styleUrls: ['./admin-add-item.component.css'],
})
export class AdminAddItemComponent implements OnInit {
  DataForm: any = {
    name: '',
    gender: 0,
    type: 1,
    description: '',
    price: 0,
    image: '',
  };

  image: any;

  typeItems: any;

  constructor(
    private itemService: ItemService,
    private file: FileService,
    private popup: PopupService
  ) {}
  ngOnInit(): void {
    this.getTypeItem();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.DataForm.image = file.name;
      this.image = file;
    }
  }

  async addItem() {
    try {
      const res: any = await this.itemService
        .addItem(this.DataForm)
        .toPromise();
      if (this.image != null) {
        try {
          const res: any = await this.file.uploadFile(this.image).toPromise();
        } catch (error) {
          console.log(error);
          this.popup.openPopup('Tải Ảnh thất bại', ' Vui lòng thử lại');
        }
      }
      this.popup.openPopup('Sản Phẩm', 'Đã thêm sản phẩm thành công');
    } catch (error) {
      console.log(error);
      this.popup.openPopup('Sản Phẩm', 'Thêm sản phẩm phẩm Không thành công');
    }
  }

  getTypeItem() {
    this.itemService.getTypes().subscribe({
      next: (res: any) => {
        this.typeItems = res.typeItems;
      },
      error: (err: any) => {
        console.log(err);
      },
    });
  }
}
