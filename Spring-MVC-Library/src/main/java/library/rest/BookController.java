package library.rest;

import library.entity.Book;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/book/*")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/book/edit")
    public String editBookPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("genres",genreRepository.findAll());
        model.addAttribute("authors",authorRepository.findAll());
        return "editbook";
    }

    @PostMapping("/book/edit")
    public String saveBook(Book book,Model model) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/book/add")
    public String addBookPage (Model model){
        model.addAttribute("genres",genreRepository.findAll());
        model.addAttribute("authors",authorRepository.findAll());
        return "addbook";
    }

    @PostMapping("/book/add")
    public String addBook (@ModelAttribute(value = "book") Book bookNew, Model model){
        bookRepository.save(bookNew);
        return "redirect:/";
    }

    @PostMapping("/book/delete")
    public String deleteBook (@RequestParam("id") int id, Model model){
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/book/comments")
    public String commentPage (@RequestParam("id") int id, Model model){
        model.addAttribute("book", bookRepository.findById(id).orElseThrow(NotFoundException::new));
        return "bookcomments";
    }
}
