package alalic.springframework.spring6reactive.mappers;

import alalic.springframework.spring6reactive.domain.Beer;
import alalic.springframework.spring6reactive.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
