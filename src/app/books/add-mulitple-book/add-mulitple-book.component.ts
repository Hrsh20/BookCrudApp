import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-add-mulitple-book',
  templateUrl: './add-mulitple-book.component.html',
  styleUrls: ['./add-mulitple-book.component.css']
})

export class AddMulitpleBookComponent implements OnInit {
  constructor(
    private bookService: BookService,
    private _snackBar: MatSnackBar
  ) {}
  bookArray: Array<any> = [];
  private newBook: any = {};

  ngOnInit(): void {
    this.newBook = {
      'bookId': '',
      'name': '',
      'author': '',
      'price': ''
    }
    this.bookArray.push(this.newBook);
  }

  addNewBookToArray() {
    this.newBook = {
      'bookId': '',
      'name': '',
      'author': '',
      'price': ''
    }
    this.bookArray.push(this.newBook);
  }

  removeBookFromArray(index: any) {
    this.bookArray.splice(index, 1);
  }

  submitBooks() {
    this.bookService.addMultipleBooks(this.bookArray).subscribe(data => {
      this._snackBar.open('Books Added Successfully')
    }, err => {
      this._snackBar.open('Book Addition Failure');
      console.log(err);
    });
  }

}
