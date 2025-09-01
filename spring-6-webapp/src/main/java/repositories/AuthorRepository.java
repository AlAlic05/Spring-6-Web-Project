package repositories;

import domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.events.Event;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
