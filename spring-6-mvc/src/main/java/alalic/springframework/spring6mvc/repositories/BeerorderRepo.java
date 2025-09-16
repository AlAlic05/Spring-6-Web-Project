package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface BeerorderRepo extends JpaRepository<BeerOrder, UUID> {

}
