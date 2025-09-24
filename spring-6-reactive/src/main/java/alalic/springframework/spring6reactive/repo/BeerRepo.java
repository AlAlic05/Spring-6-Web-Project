package alalic.springframework.spring6reactive.repo;

import alalic.springframework.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepo extends ReactiveCrudRepository<Beer, Integer>
{
}
