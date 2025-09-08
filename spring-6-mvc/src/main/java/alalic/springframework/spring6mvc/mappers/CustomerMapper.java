package alalic.springframework.spring6mvc.mappers;

import alalic.springframework.spring6mvc.entities.Customer;
import alalic.springframework.spring6mvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDto(Customer customer);
}
