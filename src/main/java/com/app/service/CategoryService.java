package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CategoryDTO;
import com.app.models.Category;
import com.app.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CategoryDTO> getAllCategories(){
		List<CategoryDTO> categories = new ArrayList<>();
		categoryRepository.findAll().stream().forEach((category) -> categories.add(modelMapper.map(category, CategoryDTO.class)));
		return categories;
	}
	
	public CategoryDTO getCategoryById(final Long id) {
		Optional<Category> cat = categoryRepository.findById(id);
		if(!cat.isPresent()) {
			return null;
		}
		
		CategoryDTO category = modelMapper.map(cat.get(),CategoryDTO.class);				
		return category;
	}
	
	public CategoryDTO addCategory(final CategoryDTO categoryDTO) {
		Category categ = modelMapper.map(categoryDTO, Category.class);
		categoryRepository.save(categ);
		
		return modelMapper.map(categ, CategoryDTO.class);
	}
}
