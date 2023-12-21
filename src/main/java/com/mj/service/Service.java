package com.mj.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mj.dto.BookDto;
import com.mj.entity.Book;
import com.mj.exception.ServiceLayerException;
import com.mj.repository.BookRepository;
import com.mj.util.LibraryConstants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class Service {
	
	public final BookRepository bookRepo;
	
	private static String dateFormat(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return dateTime.format(formatter);
	}
	
	public String addBook(BookDto bookdto) throws ServiceLayerException{
		
		Book book = new Book();
		book.setBookId(bookdto.getBookId());
		book.setName(bookdto.getName());
		book.setAuthor(bookdto.getAuthor());
		book.setPrice(bookdto.getPrice());
		book.setCreationDateTime(LocalDateTime.now());
		book.setModificationDateTime(LocalDateTime.now());
		
		try {
			bookRepo.save(book);
			return LibraryConstants.BOOK_ADDED_SUCCESSFULLY;
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public String addBooks(List<BookDto> booksRequest) throws ServiceLayerException{
		try {
			for (BookDto bookRequest : booksRequest) {
				
				Book book = new Book();
				book.setBookId(bookRequest.getBookId());
				book.setName(bookRequest.getName());
				book.setAuthor(bookRequest.getAuthor());
				book.setPrice(bookRequest.getPrice());
				book.setCreationDateTime(LocalDateTime.now());
				book.setModificationDateTime(LocalDateTime.now());
				
				bookRepo.save(book);
			}
			return LibraryConstants.BOOKS_ADDED_SUCCESSFULLY;
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public List<BookDto> getBooks() throws ServiceLayerException {
		try {
			List<BookDto> booksResponse = new ArrayList<BookDto>();
			List<Book> booksFromDb = bookRepo.findAll();
			for (Book book : booksFromDb) {
				
				BookDto bookDto = new BookDto();
				bookDto.setId(book.getId());
				bookDto.setBookId(book.getBookId());
				bookDto.setName(book.getName());
				bookDto.setAuthor(book.getAuthor());
				bookDto.setPrice(book.getPrice());
				bookDto.setCreationDateTime(dateFormat(book.getCreationDateTime()));
				bookDto.setModificationDateTime(dateFormat(book.getModificationDateTime()));
				
				booksResponse.add(bookDto);
			}
			return booksResponse;
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public BookDto getBookById(String bookId) throws ServiceLayerException{
		try {
			Optional<Book> book = bookRepo.findByBookId(bookId);
			BookDto bookResponse = null;
			if (!book.isEmpty()) {
				
				bookResponse = new BookDto();
				bookResponse.setId(book.get().getId());
				bookResponse.setBookId(book.get().getBookId());
				bookResponse.setName(book.get().getName());
				bookResponse.setAuthor(book.get().getAuthor());
				bookResponse.setPrice(book.get().getPrice());
				bookResponse.setCreationDateTime(dateFormat(book.get().getCreationDateTime()));
				bookResponse.setModificationDateTime(dateFormat(book.get().getModificationDateTime()));
				
			}
			return bookResponse;
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}

	public String deleteBook(String bookId) throws ServiceLayerException {
		try {
			Optional<Book> book = bookRepo.findByBookId(bookId);
			
			if (!book.isEmpty()) {
				bookRepo.delete(book.get());
				return LibraryConstants.BOOK_DELETED_SUCCESSFULLY;
			} else 
				return LibraryConstants.BOOK_NOT_FOUND;
			
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public String updateBook(BookDto bookUpdate) throws ServiceLayerException {
		try {
			Optional<Book> book = bookRepo.findByBookId(bookUpdate.getBookId());
			if (!book.isEmpty()) {
				
				if (bookUpdate.getName() != null) 
					book.get().setName(bookUpdate.getName());
				
				if (bookUpdate.getAuthor() != null) 
					book.get().setAuthor(bookUpdate.getAuthor());
				
				if (bookUpdate.getPrice() != null) 
					book.get().setPrice(bookUpdate.getPrice());
				
				book.get().setModificationDateTime(LocalDateTime.now());
				
				bookRepo.save(book.get());
				
				return LibraryConstants.BOOK_UPDATED_SUCCESSFULLY;
			}
			else
				return LibraryConstants.BOOK_NOT_FOUND;
		} catch (Exception e) {
			throw new ServiceLayerException(e);
		}
	}
}
