package alal.springframework.spring_6_webapp.services;

import alal.springframework.spring_6_webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();
}
