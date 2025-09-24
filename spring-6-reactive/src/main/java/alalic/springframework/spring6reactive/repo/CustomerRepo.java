package alalic.springframework.spring6reactive.repo;

import alalic.springframework.spring6reactive.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepo extends ReactiveCrudRepository<Customer, Integer> {
}
