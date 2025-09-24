package alalic.springframework.spring6reactivemongo.mappers;

import alalic.springframework.spring6reactivemongo.domain.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
