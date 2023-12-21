import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-delete-book',
  templateUrl: './delete-book.component.html',
  styleUrls: ['./delete-book.component.css']
})
export class DeleteBookComponent implements OnInit{

  bookId: string = '';
  constructor(
    private activatedRoute: ActivatedRoute,
    private bookService: BookService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.bookId = data['id'];
    })
    if (this.bookId) {
      this.bookService.deleteBook(this.bookId).subscribe(data => {
        this.router.navigate(['books']);
        this._snackBar.open('Book deleted Successfully')
      }, err => {
        this._snackBar.open('Book deletion failed!!');
      })
    }
  }
}
