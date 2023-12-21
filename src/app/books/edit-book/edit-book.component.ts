import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  bookId: string = '';
  book: any = {};
  editBookForm: FormGroup = new FormGroup({});
  constructor(
    private bookService: BookService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router
  ) { }

  numericValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (value && !/^\d+.\d+$/.test(value)) return { numeric: true };
    return null;
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.bookId = data['id'];
    })
    this.bookService.viewBook(this.bookId).subscribe(data => {
      this.book = data;
    })
    this.editBookForm = this.formBuilder.group({
      'bookId': new FormControl(this.bookId),
      'name': new FormControl(''),
      'author': new FormControl(''),
      'price': new FormControl('', [this.numericValidator])
    });
  }

  editBook() {
    // console.log(this.editBookForm.value);
    if (this.editBookForm.value.name === null || this.editBookForm.value.name === '')
      this.editBookForm.value.name = this.book.name;
    if (this.editBookForm.value.author === null || this.editBookForm.value.author === '')
      this.editBookForm.value.author = this.book.author;
    if (this.editBookForm.value.price === null || this.editBookForm.value.price === '')
      this.editBookForm.value.price = this.book.price;
    // console.log(this.editBookForm.value);
    else this.editBookForm.value.price = '₹' + this.editBookForm.value.price;
    if (this.editBookForm.value.name === this.book.name && this.editBookForm.value.author === this.book.author && this.editBookForm.value.price === this.book.price) {
      this._snackBar.open('Nothing Changed!!');
    } else {
      this.bookService.editBook(this.editBookForm.value).subscribe(data => {
        this._snackBar.open('Book Updated Successfully');
      }, err => {
        this._snackBar.open('Book Updation failed');
        console.log(err);
      });
    }
    this.editBookForm = this.formBuilder.group({
      'bookId': new FormControl(this.bookId),
      'name': new FormControl(''),
      'author': new FormControl(''),
      'price': new FormControl('', [this.numericValidator])
    });
    this.router.navigate(['books']);
  }

}
