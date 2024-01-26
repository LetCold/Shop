import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { ApiService } from './api.service';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private api: ApiService) { }

  uploadFile(file: File): any {
    if (!file) {
      const errorMessage = "Vui lòng upload file";
      return of({ error: errorMessage });
    }

    if (file.size <= 0) {
      const errorMessage = "file rỗng";
      return of({ error: errorMessage });
    }

    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.api.postApi("/admin/item/upload", formData);
  }
}
