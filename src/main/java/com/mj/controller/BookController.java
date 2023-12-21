package com.mj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.dto.BookDto;
import com.mj.dto.ResponseDto;
import com.mj.exception.ServiceLayerException;
import com.mj.service.Service;
import com.mj.util.LibraryConstants;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/library")
@AllArgsConstructor
@CrossOrigin
public class BookController {
	public final Service service;

	@PostMapping("/addBook")
	public ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookdto) {
		ResponseEntity<ResponseDto> responseEntity = null;
		try {
			String responseMsg = service.addBook(bookdto);
			
			ResponseDto response = new ResponseDto(responseMsg);
			responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (ServiceLayerException e) {
			String error = e.getRootCause();
			
			ResponseDto response = new ResponseDto(error);
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		
		return responseEntity;
	}

	@PostMapping("/addBooks")
	public ResponseEntity<ResponseDto> addBooks(@RequestBody List<BookDto> books) {
		ResponseEntity<ResponseDto> responseEntity = null;
		try {
			String responseMsg = service.addBooks(books);

			ResponseDto response = new ResponseDto(responseMsg);
			responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (ServiceLayerException e) {
			String error = e.getRootCause();

			ResponseDto response = new ResponseDto(error);
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

	@GetMapping("/getBooks")
	public ResponseEntity<?> getBooks() {
		ResponseEntity<?> responseEntity = null;
		try {
			List<BookDto> books = service.getBooks();
			
			if (0 != books.size())
				responseEntity = new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
			else {
				ResponseDto response = new ResponseDto(LibraryConstants.NO_BOOKS_IN_DATABASE);
				responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
			}
			
		} catch (ServiceLayerException e) {
			String error = e.getRootCause();

			ResponseDto response = new ResponseDto(error);
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

	@GetMapping("/getBookById/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable String bookId) {
		ResponseEntity<?> responseEntity = null;
		try {
			BookDto bookResponse = service.getBookById(bookId);
			if (null != bookResponse) 
				responseEntity = new ResponseEntity<BookDto>(bookResponse, HttpStatus.OK);
			else {
				ResponseDto response = new ResponseDto(LibraryConstants.BOOK_NOT_FOUND);
				responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
			}
		} catch (ServiceLayerException e) {
			
			ResponseDto response = new ResponseDto(LibraryConstants.BOOK_FETCHING_FAILURE + "  -> " + e.getRootCause());
			responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		
		return responseEntity;
	}
	
	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<ResponseDto> deleteBook(@PathVariable String bookId) {
		ResponseEntity<ResponseDto> responseEntity = null;
		
		try {
			String responseMsg = service.deleteBook(bookId);
			
			ResponseDto response = new ResponseDto(responseMsg);
			responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
		} catch (ServiceLayerException e) {
			
			ResponseDto response = new ResponseDto(LibraryConstants.BOOK_DELETION_FAILED + " -> " + e.getRootCause());
			responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

	@PutMapping("/updateBook")
	public ResponseEntity<ResponseDto> updateBook(@RequestBody BookDto bookUpdate) {
		ResponseEntity<ResponseDto> responseEntity = null;
		try {
			String responseMsg = service.updateBook(bookUpdate);
			
			ResponseDto response = new ResponseDto(responseMsg);
			responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
		} catch (ServiceLayerException e) {
			
			ResponseDto response = new ResponseDto(LibraryConstants.BOOK_UPDATION_FAILED+ " -> " + e.getRootCause());
			responseEntity = new ResponseEntity<ResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
