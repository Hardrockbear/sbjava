package library.repository;

import library.entity.*;
import org.springframework.data.repository.CrudRepository;
import library.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer>{
    List<Book> findByAuthorAndGenre(Author author, Genre genre);
}
