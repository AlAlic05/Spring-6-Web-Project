package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Beer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();

    Beer getBearById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeer(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchBeer(UUID beerId, Beer beer);
}
