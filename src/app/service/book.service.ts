import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  
  baseUrl: string = 'http://localhost:8080/library/';

  listOfBooks = []

  constructor(private httpClient: HttpClient) { }

  listBooks() {
    return this.httpClient.get(this.baseUrl + 'getBooks');
  }
  
  viewBook(id: string) {
    return this.httpClient.get(this.baseUrl + `getBookById/${id}`);
  }

  addBook(book: Object) {
    return this.httpClient.post(this.baseUrl + 'addBook', book);
  }
  
  deleteBook(id: string) {
    return this.httpClient.delete(this.baseUrl + `deleteBook/${id}`);
  }

  editBook(book: Object) {
    return this.httpClient.put(this.baseUrl + 'updateBook', book);
  }
}
