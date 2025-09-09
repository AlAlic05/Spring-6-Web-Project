package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.mappers.BeerMapper;
import alalic.springframework.spring6mvc.mappers.CustomerMapper;
import alalic.springframework.spring6mvc.model.CustomerDTO;
import alalic.springframework.spring6mvc.repositories.BeerRepo;
import alalic.springframework.spring6mvc.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> listCustomer() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepo.findById(id)
                .orElse(null)));
    }

    @Override
    public CustomerDTO savedCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(customerRepo.save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepo.findById(customerId).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            foundCustomer.setCreatedDate(customer.getCreatedDate());
            foundCustomer.setLastModifiedDate(LocalDateTime.now());
            foundCustomer.setVersion(customer.getVersion());
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepo.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean deleteCustomerById(UUID beerId) {
        if (customerRepo.existsById(beerId)) {
            customerRepo.existsById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepo.findById(customerId).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customer.getCustomerName())){
                foundCustomer.setCustomerName(customer.getCustomerName());
            }
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepo.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
}
