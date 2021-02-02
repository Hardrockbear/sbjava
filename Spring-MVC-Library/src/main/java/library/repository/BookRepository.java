package library.repository;

import library.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByAuthorAndGenre(Author author, Genre genre);

    @Query("SELECT b from Book b LEFT JOIN FETCH b.comments WHERE b.id = :id")
    Book findByIdWithComments(@Param("id") int id);
}
