package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {
    List<Beer> findByBeerNameLikeIgnoreCase(String beerName);
    List<Beer> findByBeerStyle(BeerStyle beerStyle);
}
