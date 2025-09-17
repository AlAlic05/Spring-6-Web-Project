package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.mappers.BeerMapper;
import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.model.BeerStyle;
import alalic.springframework.spring6mvc.repositories.BeerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Beer> beerPage;

        if(StringUtils.hasText(beerName) && beerStyle == null) {
            beerPage = listBeersByName(beerName, pageRequest);
        }
        else if (beerName == null && beerStyle != null){
            beerPage = listBeersByStyle(beerStyle, pageRequest);
        }
        else if(StringUtils.hasText(beerName) && beerStyle != null){
            beerPage = findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(beerName, beerStyle, pageRequest);
        }
        else {
            beerPage = beerRepo.findAll(pageRequest);
        }

        return beerPage.map(beerMapper::beerToBeerDTO);
    }

    Page<Beer> listBeersByName(String beerName, PageRequest pageRequest) {
        return beerRepo.findByBeerNameLikeIgnoreCase("%" + beerName + "%", pageRequest);
    }

    Page<Beer> listBeersByStyle(BeerStyle beerStyle, PageRequest pageRequest) {
        return beerRepo.findByBeerStyle(beerStyle, pageRequest);
    }

    Page<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest) {
        return beerRepo.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%", beerStyle, pageRequest);
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null || pageSize <= 0) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else if (pageSize > 1000) {
            queryPageSize = 1000;
        } else {
            queryPageSize = pageSize;
        }

        Sort sort = Sort.by(Sort.Order.asc("beerName"));
        return PageRequest.of(queryPageNumber, queryPageSize, sort);
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
            foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            foundBeer.setVersion(beer.getVersion());
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
