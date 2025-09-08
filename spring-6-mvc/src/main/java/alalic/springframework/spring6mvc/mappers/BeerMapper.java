package alalic.springframework.spring6mvc.mappers;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtotoBeer(BeerDTO beerDTO);
    BeerDTO beerToBeerDTO(Beer beer);
}
