package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.entities.BeerOrder;
import alalic.springframework.spring6mvc.entities.BeerOrderShipment;
import alalic.springframework.spring6mvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BeerorderRepoTest {

    @Autowired
    BeerOrderRepo beerorderRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BeerRepo beerRepo;

    Customer testCustomer;
    Beer testBeer;


    @BeforeEach
    void setUp() {
        testCustomer = customerRepo.findAll().get(0);
        testBeer = beerRepo.findAll().get(0);
    }

    @Test
    void testBeerOrders(){
        System.out.println(beerorderRepo.count());
        System.out.println(customerRepo.count());
        System.out.println(beerRepo.count());
        System.out.println(testCustomer.getCustomerName());
        System.out.println(testBeer.getBeerName());
    }

    @Transactional
    @Test
    void testBearorders() {
        BeerOrder beerorder = BeerOrder.builder()
                .customerRef("Test Order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("12345")
                        .build())
                .build();

        BeerOrder savedBeerOrder = beerorderRepo.save(beerorder);
        System.out.println(savedBeerOrder.getCustomerRef());
    }
}