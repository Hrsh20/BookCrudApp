package com.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	private Integer id;
	private String bookId;
	private String name;
	private String author;
	private String price;
	private String creationDateTime;
	private String modificationDateTime;
}
