package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, BeerDTO> beers;

    public BeerServiceImpl() {
        this.beers = new HashMap<>();

        BeerDTO b1 = BeerDTO.builder()
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
        BeerDTO b2 = BeerDTO.builder()
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
        BeerDTO b3 = BeerDTO.builder()
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
    public List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle) {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Optional<BeerDTO> getBearById(UUID id) {
        log.debug("Get Beer by Id - in service");
        return Optional.of(beers.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder()
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
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beers.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        existing.setUpdateDate(LocalDateTime.now());

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteBeerById(UUID beerId) {
        beers.remove(beerId);
        return true;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beers.get(beerId);
        if (StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }

        return Optional.of(existing);
    }
}
