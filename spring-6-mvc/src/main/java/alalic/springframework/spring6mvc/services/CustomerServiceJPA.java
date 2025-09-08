package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.mappers.BeerMapper;
import alalic.springframework.spring6mvc.mappers.CustomerMapper;
import alalic.springframework.spring6mvc.model.CustomerDTO;
import alalic.springframework.spring6mvc.repositories.BeerRepo;
import alalic.springframework.spring6mvc.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return null;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public void deleteCustomerById(UUID beerId) {

    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

    }
}
