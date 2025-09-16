package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    BeerRepo beerRepo;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testBeer = beerRepo.findAll().get(0);
    }

    @Transactional
    @Test
    void testAddCategory(){
        Category savedCategory = categoryRepo.save(Category.builder()
                .description("Ales")
                .build());

        testBeer.addCategory(savedCategory);
        Beer savedBeer = beerRepo.save(testBeer);

        System.out.println(savedBeer.getBeerName());
    }
}