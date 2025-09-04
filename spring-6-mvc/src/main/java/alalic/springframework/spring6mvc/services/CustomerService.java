package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomer();

    Customer getCustomerById(UUID id);

    Customer savedCustomer(Customer customer);

    void updateCustomer(UUID customerId, Customer customer);

    void deleteCustomerById(UUID beerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
