package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Beer;
import alalic.springframework.spring6mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBearById(UUID id) {
        log.debug("Get Beer by Id - in service");
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Bee(r) The Movie")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc(UUID.randomUUID().toString())
                .price(new BigDecimal("12.99"))
                .quantityOnHand(233)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
