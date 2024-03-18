package ru.otus.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @GetMapping({"/", "/books"})
    public String getAll() {
        return "books";
    }

    @GetMapping("/book/{id}")
    public String get(@PathVariable long id, Model model) {
        model.addAttribute("bookId", id);
        return "book";
    }

    @GetMapping("/book")
    public String get(@RequestParam("bookId") long id) {
        return "redirect:/book/" + id;
    }

    @GetMapping("/book/new")
    public String add(Model model) {
        model.addAttribute("refer", "/book/new");
        return "editBook";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("bookId", id);
        model.addAttribute("refer", "/book/edit");
        return "editBook";
    }
}
