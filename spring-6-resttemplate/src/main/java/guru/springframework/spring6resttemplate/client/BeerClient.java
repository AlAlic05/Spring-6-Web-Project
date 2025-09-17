package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface
BeerClient {
    Page<BeerDTO> listBeers();
    Page<BeerDTO> listBeers(String beerName, String beerStyle, Integer pageNumber, Integer pageSize);
    BeerDTO getBeerById(UUID id);

    BeerDTO createBeer(BeerDTO newDto);

    BeerDTO updateBeer(BeerDTO beerDTO);

    void deleteBeer(UUID id);
}
