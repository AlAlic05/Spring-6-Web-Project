package alal.springframework.spring_6_webapp.services;

import alal.springframework.spring_6_webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
