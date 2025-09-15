package alalic.springframework.spring6mvc.bootstrap;

import alalic.springframework.spring6mvc.repositories.BeerRepo;
import alalic.springframework.spring6mvc.repositories.CustomerRepo;
import alalic.springframework.spring6mvc.services.BeerCSVService;
import alalic.springframework.spring6mvc.services.BeerCSVServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(BeerCSVServiceImpl.class)
class BootstrapDataTest {

    @Autowired
    BeerRepo beerRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    BeerCSVService beerCSVService;
    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beerRepo, customerRepo, beerCSVService);
    }

    @Test
    void Testrun() throws Exception{
        bootstrapData.run(null );
        assertThat(beerRepo.count()).isEqualTo(2413);
        assertThat(customerRepo.count()).isEqualTo(3);
    }
}