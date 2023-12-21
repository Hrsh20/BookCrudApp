import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  bookId: string = '';
  book: any = {}
  constructor(
    private bookService: BookService, 
    private activatedRoute: ActivatedRoute
  ) { }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.bookId = data['id'];
    });
    this.bookService.viewBook(this.bookId).subscribe(data => {
      this.book = data;
    });
  }
}
