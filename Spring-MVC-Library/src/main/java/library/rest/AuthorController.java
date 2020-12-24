package library.rest;

import library.entity.Author;
import library.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/author/*")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
}
