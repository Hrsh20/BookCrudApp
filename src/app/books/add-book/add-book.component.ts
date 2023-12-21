import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BookService } from 'src/app/service/book.service';
import { AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})

export class AddBookComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private bookService: BookService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) { }

  addBookForm: FormGroup = new FormGroup({});

  ngOnInit(): void {
    this.addBookForm = this.formBuilder.group({
      'bookId': new FormControl('', [Validators.required, this.bookIdValidator]),
      'name': new FormControl('', Validators.required),
      'author': new FormControl('', Validators.required),
      'price': new FormControl('', [Validators.required, this.numericValidator])
    });

  }

  numericValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (value && !/^\d+.\d+$/.test(value)) return { numeric: true };
    return null;
  }

  bookIdValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (value && !/^BK\d{3}$/.test(value)) return { bookId : true }
    return null;
  }

  addBook() {
    this.addBookForm.value.price = '₹' + this.addBookForm.value.price;
    this.bookService.addBook(this.addBookForm.value).subscribe((data: any) => {
      this._snackBar.open('Book Added Successfully');
    }, (err) => {
      this._snackBar.open('Book Addition Failed');
      console.log(err);
    });
    this.addBookForm = this.formBuilder.group({
      'bookId': new FormControl('', [Validators.required, this.bookIdValidator]),
      'name': new FormControl('', Validators.required),
      'author': new FormControl('', Validators.required),
      'price': new FormControl('', [Validators.required, this.numericValidator])
    });
    this.router.navigate(['books']);
  }

}
