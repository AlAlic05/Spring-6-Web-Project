package alalic.springframework.spring6reactivesamples.repo;

import alalic.springframework.spring6reactivesamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepo {
    Mono<Person> getById(Integer id);
    Flux<Person> getAll();
}
