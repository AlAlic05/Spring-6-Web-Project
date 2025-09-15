package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.mappers.BeerMapper;
import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.repositories.BeerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers(String beerName) {
        List<Beer> beerList;

        if(StringUtils.hasText(beerName)){
            beerList = listBeersByname(beerName);
        }
        else {
            beerList = beerRepo.findAll();
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDTO)
                .collect(Collectors.toList());
    }

    List<Beer> listBeersByname(String beerName){
        return beerRepo.findByBeerNameLikeIgnoreCase("%" + beerName + "%");
    }

    @Override
    public Optional<BeerDTO> getBearById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDTO(beerRepo.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDTO(beerRepo.save(beerMapper.beerDtotoBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepo.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setPrice(beer.getPrice());
            foundBeer.setUpc(beer.getUpc());
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDTO(beerRepo.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean deleteBeerById(UUID beerId) {
        if(beerRepo.existsById(beerId)){
            beerRepo.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepo.findById(beerId).ifPresentOrElse(foundBeer -> {
            if(StringUtils.hasText(beer.getBeerName())) {
                foundBeer.setBeerName(beer.getBeerName());
            }
            if(beer.getBeerStyle() != null) {
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if(beer.getPrice() != null) {
                foundBeer.setPrice(beer.getPrice());
            }
            if(StringUtils.hasText(beer.getUpc())) {
                foundBeer.setUpc(beer.getUpc());
            }
            if(beer.getQuantityOnHand() != null) {
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
            atomicReference.set(Optional.of(beerMapper
                .beerToBeerDTO(beerRepo.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }
}
