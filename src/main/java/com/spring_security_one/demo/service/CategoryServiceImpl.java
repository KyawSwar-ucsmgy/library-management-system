package com.spring_security_one.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Category;
import com.spring_security_one.demo.model.CategoryDto;
import com.spring_security_one.demo.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepo;
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategories_type(categoryDto.getCategories_type());
        categoryRepo.save(category);
    }

    @Override
    public void editCategory(long id, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category != null) {
            category.setCategories_type(categoryDto.getCategories_type());
            categoryRepo.save(category);
        }
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category getElementById(long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

	@Override
	public long countCategory() {
		// TODO Auto-generated method stub
		return categoryRepo.count();
	}
}
