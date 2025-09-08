package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
}
