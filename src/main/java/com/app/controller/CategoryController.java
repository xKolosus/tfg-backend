package com.app.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDTO;
import com.app.exceptions.NotFoundException;
import com.app.service.CategoryService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@Produces("application/json")
	public @ResponseBody List<CategoryDTO> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("{id}")
	@Produces("application/json")
	public @ResponseBody CategoryDTO getArticleById(@PathVariable final Long id) {
		CategoryDTO category = categoryService.getCategoryById(id);
		if(category != null) {
			return category;
		} else {
			throw new NotFoundException();
		}
	}
}
