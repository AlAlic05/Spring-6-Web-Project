package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        this.customers=new HashMap<>();

        CustomerDTO c1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Sebastian Fettl")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO c2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Manuelsn")
                .version(2)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO c3 = CustomerDTO.builder()
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
    public List<CustomerDTO> listCustomer() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        log.debug("Get customer by id - in service");
        return Optional.of(customers.get(id));
    }

    @Override
    public CustomerDTO savedCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
            .id(UUID.randomUUID())
            .createdDate(LocalDateTime.now())
            .lastModifiedDate(LocalDateTime.now())
            .customerName(customer.getCustomerName())
            .version(customer.getVersion())
            .build();

        customers.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customers.get(customerId);
        existing.setCustomerName(customer.getCustomerName());
        existing.setLastModifiedDate(LocalDateTime.now());
        existing.setVersion(existing.getVersion() + 1);

        customers.put(existing.getId(), existing);
        return null;
    }

    @Override
    public Boolean deleteCustomerById(UUID beerId) {
        customers.remove(beerId);
        return true;
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customers.get(customerId);

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
