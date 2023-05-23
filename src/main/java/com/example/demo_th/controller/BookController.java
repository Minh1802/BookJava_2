package com.example.demo_th.controller;

import com.example.demo_th.entity.Book;
import com.example.demo_th.services.BookServices;
import com.example.demo_th.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookServices.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public  String  addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
        public String addBook(@Validated @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("book", new Book());
            model.addAttribute("category", categoryService.getAllCategories());
            return "book/add";
        }
        bookServices.addBook(book);
        return"redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm (@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Book editBook = bookServices.getBookById(id);

        if (editBook != null) {
            model.addAttribute("book", editBook);
            return "book/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook (@ModelAttribute("book") Book updatedBook) {

        bookServices.updateBook(updatedBook);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookServices.getBookById(id);
        bookServices.deleteBook(id);
        return "redirect:/books";
    }



}
