package library.rest;

import library.entity.*;
import library.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import library.repository.BookRepository;

@Controller
public class LibraryController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public LibraryController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/")
    public String libraryPage(@RequestParam(required= false, defaultValue="") Author author,@RequestParam(required= false, defaultValue="") Genre genre, Model model) {
        if(author != null && genre != null)
        {
            model.addAttribute("books",bookRepository.findByAuthorAndGenre(author,genre));
        }else {
            model.addAttribute("books", bookRepository.findAll());
        }
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("filter_authors", model.getAttribute("authors"));
        model.addAttribute("filter_genres", model.getAttribute("genres"));
        return "library";
    }

    @GetMapping("/filter")
    public String filter (@ModelAttribute("author") Author author, @ModelAttribute("genre") Genre genre, Model model){
        return this.libraryPage(author,genre,model);
    }
}
