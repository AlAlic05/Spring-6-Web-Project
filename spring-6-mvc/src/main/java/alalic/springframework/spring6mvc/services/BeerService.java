package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle);

    Optional<BeerDTO> getBearById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
