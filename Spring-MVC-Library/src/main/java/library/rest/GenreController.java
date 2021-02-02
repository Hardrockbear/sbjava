package library.rest;

import library.entity.Genre;
import library.repository.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/genre/*")
public class GenreController {
    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
}
