package alalic.springframework.spring6reactivemongo.mappers;

import alalic.springframework.spring6reactivemongo.domain.Customer;
import alalic.springframework.spring6reactivemongo.modles.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
