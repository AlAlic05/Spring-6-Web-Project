package alalic.springframework.spring6reactivesamples.repo;

import alalic.springframework.spring6reactivesamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class PersonRepoImpl implements PersonRepo {
    private final List<Person> people = List.of(
            Person.builder().id(1).firstName("John").lastName("Doe").build(),
            Person.builder().id(2).firstName("Jane").lastName("Doe").build(),
            Person.builder().id(3).firstName("Jason").lastName("Doe").build(),
            Person.builder().id(4).firstName("Jan").lastName("Doe").build());

    @Override
    public Mono<Person> getById(Integer id) {
        return Flux.fromIterable(people)
                .filter(person -> person.getId().equals(id))
                .next();
    }

    @Override
    public Flux<Person> getAll() {
        return Flux.fromIterable(people);
    }
}
