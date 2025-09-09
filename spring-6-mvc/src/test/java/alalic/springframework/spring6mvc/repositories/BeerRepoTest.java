package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepoTest {
    @Autowired
    BeerRepo beerRepo;

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepo.save(Beer.builder()
                        .beerName("My New Beer")
                        .beerStyle(BeerStyle.GOSE)
                        .upc("123456789012")
                        .price(new BigDecimal("12.99"))
                .build());

        beerRepo.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong(){
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepo.save(Beer.builder()
                    .beerName("Beer with a very very very very very very very very looooooooooooooooooooong name")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("123456789012")
                    .price(new BigDecimal("12.99"))
                    .build());

            beerRepo.flush();
        });
    }
}