import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './books/book-list/book-list.component';
import { BookComponent } from './books/book/book.component';
import { AddBookComponent } from './books/add-book/add-book.component';
import { EditBookComponent } from './books/edit-book/edit-book.component';
import { DeleteBookComponent } from './books/delete-book/delete-book.component';

const routes: Routes = [
  {
    path: 'books',
    children : [
      {
        path: '',
        component: BookListComponent
      },
      {
        path: 'viewBook/:id',
        component: BookComponent
      },
      {
        path: 'editBook/:id',
        component: EditBookComponent
      },
      {
        path: 'deleteBook/:id',
        component: DeleteBookComponent
      }
    ]
  },
  
  {
    path: 'addBook',
    component: AddBookComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
