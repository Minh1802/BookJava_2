package com.example.demo_th.services;


import com.example.demo_th.entity.Book;
import com.example.demo_th.entity.Category;
import com.example.demo_th.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public ICategoryRepository categoryRepository;

    public List<Category> getAllCategories() { return categoryRepository.findAll();}

    public Category getCategoryById(Long id) { return categoryRepository.findById(id).orElse(null);}

    public Category saveCategory(Category category) { return categoryRepository.save(category);}

    public void deleteCategory(Long id) { categoryRepository.deleteById(id);}

    public void addCategory(Category category){
        categoryRepository.save(category);

    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }
}
