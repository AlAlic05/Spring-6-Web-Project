package alalic.springframework.spring6mvc.controller;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.entities.Customer;
import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.model.CustomerDTO;
import alalic.springframework.spring6mvc.repositories.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController controller;

    @Autowired
    CustomerRepo repository;

    @Test
    void testListAllCustomers(){
        List<CustomerDTO> dtos = controller.listCustomers();
        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        repository.deleteAll();
        List<CustomerDTO> dtos = controller.listCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetById() {
        Customer customer = repository.findAll().get(0);
        CustomerDTO customerDTO = controller.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testCustomerIdIsNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.getCustomerById(UUID.randomUUID());
        });
    }

}