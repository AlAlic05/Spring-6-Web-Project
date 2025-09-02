package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.Beer;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    Beer getBearById(UUID id);
}
