package alal.springframework.spring_6_webapp.controllers;

import alal.springframework.spring_6_webapp.services.AuthorService;
import alal.springframework.spring_6_webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String getBooks(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }
}
