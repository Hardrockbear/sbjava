package library;

import library.entity.*;
import library.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import library.repository.BookRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public App (BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void init() {
        bookRepository.save(new Book("The Tragical Historie of Hamlet, Prince of Denmarke",new Genre("Tragedy"),new Author("William Shakespeare")));
        bookRepository.save(new Book("Moby-Dick",new Genre("Novel"),new Author("Herman Melville")));
        bookRepository.save(new Book("The Hitchhiker's Guide to the Galaxy",new Genre("Comedy Science Fiction"),new Author("Douglas Adams")));
        bookRepository.save(new Book("The Lord of the Rings",new Genre("Fantasy"),new Author("John Ronald Reuel Tolkien")));

        Book hamletBook = bookRepository.findByIdWithComments(1);
        hamletBook.addComment("Everyone");
        hamletBook.addComment("Knows");
        hamletBook.addComment("Hamlet");
        bookRepository.save(hamletBook);
    }
}
