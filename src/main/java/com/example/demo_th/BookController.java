package com.example.demo_th;

import com.example.demo_th.entity.Book;
import com.example.demo_th.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookServices bookServices;

    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookServices.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

}
