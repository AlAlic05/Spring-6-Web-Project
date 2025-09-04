package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Beer;
import alalic.springframework.spring6mvc.model.Customer;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        this.customers=new HashMap<>();

        Customer c1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Sebastian Fettl")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer c2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Manuelsn")
                .version(2)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer c3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("John Doe")
                .version(3)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(c1.getId(), c1);
        customers.put(c2.getId(), c2);
        customers.put(c3.getId(), c3);
    }

    @Override
    public List<Customer> listCustomer() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        log.debug("Get customer by id - in service");
        return Optional.of(customers.get(id));
    }

    @Override
    public Customer savedCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
            .id(UUID.randomUUID())
            .createdDate(LocalDateTime.now())
            .lastModifiedDate(LocalDateTime.now())
            .customerName(customer.getCustomerName())
            .version(1)
            .build();

        customers.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existing = customers.get(customerId);
        existing.setCustomerName(customer.getCustomerName());
        existing.setLastModifiedDate(LocalDateTime.now());
        existing.setVersion(existing.getVersion() + 1);

        customers.put(existing.getId(), existing);
    }

    @Override
    public void deleteCustomerById(UUID beerId) {
        customers.remove(beerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customers.get(customerId);

        if (customer.getCustomerName() != null) {
            existing.setCustomerName(customer.getCustomerName());
        }
        if (customer.getVersion() != null) {
            existing.setVersion(customer.getVersion());
        }
        if (customer.getCreatedDate() != null) {
            existing.setCreatedDate(customer.getCreatedDate());
        }
        if( customer.getLastModifiedDate() != null) {
            existing.setLastModifiedDate(customer.getLastModifiedDate());
        }


        customers.put(existing.getId(), existing);
    }
}
