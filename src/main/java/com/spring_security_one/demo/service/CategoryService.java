package com.spring_security_one.demo.service;

import java.util.List;

import com.spring_security_one.demo.model.Category;
import com.spring_security_one.demo.model.CategoryDto;


public interface CategoryService {

//	List<Category> getAllCategories();
//	void createCategory(CategoryDto categotyDto);
//	void deleteCategory(long id);
//	void getCategoryById(long id);
//	void editCategory(long id, CategoryDto categoryDto);
	
	List<Category> getAllCategories();
	void createCategory(CategoryDto categoryDto);
	void editCategory(long id, CategoryDto categoryDto);
	void deleteCategory(long id);
	Category getElementById(long id);
	Category getCategoryById(long id);

	List<Category> getAllCategory();
	long countCategory();
}
