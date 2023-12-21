import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit{

  listBooks: any;
  displayedColumns = ['bookId', 'name', 'author', 'price', 'creationDateTime', 'modificationDateTime', 'actions'];

  constructor(private bookService: BookService) { }
  ngOnInit(): void {
    this.bookService.listBooks().subscribe((data: any) => {
      this.listBooks = data;
    });
  }
}
