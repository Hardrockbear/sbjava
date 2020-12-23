package library.rest;

import library.entity.*;
import library.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import library.repository.BookRepository;

@Controller
public class RestController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public RestController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
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

    @GetMapping("/author/edit")
    public String editAuthorPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("author", authorRepository.findById(id).orElseThrow(NotFoundException::new));
        return "editauthor";
    }

    @PostMapping("/author/edit")
    public String saveAuthor(Author author, Model model) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/author/add")
    public String addAuthorPage (Model model){
        return "addauthor";
    }

    @PostMapping("/author/add")
    public String addAuthor (@ModelAttribute(value = "author") Author authorNew, Model model){
        authorRepository.save(authorNew);
        return "redirect:/";
    }

    @PostMapping("/author/delete")
    public String deleteAuthor (@RequestParam("id") int id, Model model){
        authorRepository.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/genre/edit")
    public String editGenrePage(@RequestParam("id") int id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id).orElseThrow(NotFoundException::new));
        return "editgenre";
    }

    @PostMapping("/genre/edit")
    public String saveGenre(Genre genre, Model model) {
        genreRepository.save(genre);
        return "redirect:/";
    }

    @GetMapping("/genre/add")
    public String addGenrePage (Model model){
        return "addgenre";
    }

    @PostMapping("/genre/add")
    public String addGenre (@ModelAttribute(value = "genre") Genre genreNew, Model model){
        if (genreRepository.findByName(genreNew.getName()).isEmpty()) {
            genreRepository.save(genreNew);
        }
        return "redirect:/";
    }

    @PostMapping("/genre/delete")
    public String deleteGenre (@RequestParam("id") int id, Model model){
        genreRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter (@ModelAttribute("author") Author author, @ModelAttribute("genre") Genre genre, Model model){
        return this.libraryPage(author,genre,model);
    }
}
