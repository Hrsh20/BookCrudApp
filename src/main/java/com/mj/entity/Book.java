package com.mj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Book_details")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 10, nullable = false, unique = true)
	private String bookId;
	@Column(nullable = false)
	private String name;
	@Column(length = 50, nullable = false)
	private String author;
	@Column(length = 10, nullable = false)
	private String price;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime creationDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modificationDateTime;	
}
