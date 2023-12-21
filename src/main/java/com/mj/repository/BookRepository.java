package com.mj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.entity.Book;

@Repository(value = "bookRepo")
public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByBookId(String bookId);

}
