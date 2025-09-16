package alalic.springframework.spring6mvc.repositories;

import alalic.springframework.spring6mvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
}
