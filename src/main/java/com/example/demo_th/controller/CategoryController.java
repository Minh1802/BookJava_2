package com.example.demo_th.controller;
import com.example.demo_th.entity.Book;
import com.example.demo_th.entity.Category;
import com.example.demo_th.services.BookServices;
import com.example.demo_th.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorys")
public class CategoryController {



    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model){
        List<Category> categorys = categoryService.getAllCategories();
        model.addAttribute("categorys", categorys);
        return "category/list";
    }

    @GetMapping("/add")
    public  String  addCategoryForm(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@Validated @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("category", new Category());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "category/add";
        }
        categoryService.addCategory(category);
        return"redirect:/categorys";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm (@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Category editCategory = categoryService.getCategoryById(id);

        if (editCategory != null) {
            model.addAttribute("category", editCategory);
            return "category/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editCategory (@ModelAttribute("book") Category updatedCategory) {

        categoryService.updateCategory(updatedCategory);
        return "redirect:/categorys";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);
        return "redirect:/categorys";
    }


}
