package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Beer;
import alalic.springframework.spring6mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beers;

    public BeerServiceImpl() {
        this.beers = new HashMap<>();

        Beer b1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Bee(r) The Movie")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc(UUID.randomUUID().toString())
                .price(new BigDecimal("12.99"))
                .quantityOnHand(233)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Beer b2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Beer(hard)")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc(UUID.randomUUID().toString())
                .price(new BigDecimal("43.92"))
                .quantityOnHand(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Beer b3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Beerreichung für die ganze Familie")
                .beerStyle(BeerStyle.STOUT)
                .upc(UUID.randomUUID().toString())
                .price(new BigDecimal("22.22"))
                .quantityOnHand(12345)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beers.put(b1.getId(), b1);
        beers.put(b2.getId(), b2);
        beers.put(b3.getId(), b3);
    }

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Optional<Beer> getBearById(UUID id) {
        log.debug("Get Beer by Id - in service");
        return Optional.of(beers.get(id));
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .version(beer.getVersion())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .build();

        beers.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beers.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        existing.setUpdateDate(LocalDateTime.now());

        beers.put(existing.getId(), existing);
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beers.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beers.get(beerId);
        if (beer.getBeerName() != null) {
            existing.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }
        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }
        if (beer.getUpc() != null) {
            existing.setUpc(beer.getUpc());
        }
        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }
        existing.setUpdateDate(LocalDateTime.now());

        beers.put(existing.getId(), existing);
    }
}
