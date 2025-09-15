package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {
    Page<Beer> findByBeerNameLikeIgnoreCase(String beerName, Pageable pageable);
    Page<Beer> findByBeerStyle(BeerStyle beerStyle, Pageable pageable);
    Page<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

}
