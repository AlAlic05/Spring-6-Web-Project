package alalic.springframework.spring6reactive.repo;

import alalic.springframework.spring6reactive.config.DatabaseConfig;
import alalic.springframework.spring6reactive.domain.Beer;
import alalic.springframework.spring6reactive.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestCustomer()));
    }

    @Test
    void saveNewCustomer() {
        customerRepo.save(getTestCustomer())
                .subscribe(customer -> {
                    System.out.println(customer.toString());
                });
    }

    public static Customer getTestCustomer() {
        return Customer.builder()
                .customerName("Space Dust")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
