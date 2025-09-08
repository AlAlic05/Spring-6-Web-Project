package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBearById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
