package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("localmysql")
public class MySqlIT {
    @Container
    @ServiceConnection
    static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.0").withDatabaseName("restdb");

    @Autowired
    BeerRepo beerRepo;

    @Test
    void testListBeers(){
        List<Beer> beers = beerRepo.findAll();

        assertThat(beers.size()).isGreaterThan(0);
    }
}
