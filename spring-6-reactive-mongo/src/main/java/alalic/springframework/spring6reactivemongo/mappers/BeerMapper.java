package alalic.springframework.spring6reactivemongo.mappers;

import alalic.springframework.spring6reactivemongo.domain.Beer;
import alalic.springframework.spring6reactivemongo.modles.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
