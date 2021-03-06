package library.repository;

import library.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Integer>{
    Optional<Genre> findByName(String name);
}
