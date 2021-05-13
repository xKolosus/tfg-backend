package com.app.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long categoryId;
	private String categoryName;
}
