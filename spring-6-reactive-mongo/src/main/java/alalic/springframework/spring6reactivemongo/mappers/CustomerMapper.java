package alalic.springframework.spring6reactivemongo.mappers;

import alalic.springframework.spring6reactivemongo.domain.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
