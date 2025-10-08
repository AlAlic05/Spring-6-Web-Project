package alalic.springframework.spring6reactivemongo.repos;

import alalic.springframework.spring6reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepo extends ReactiveMongoRepository<Customer, String> {

}
