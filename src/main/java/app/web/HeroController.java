package app.web;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.service.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HeroController {

    private final ModelMapper modelMapper;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;

    public HeroController(ModelMapper modelMapper, HeroRepository heroRepository, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hero/create")
    public String heroCreate() {
        return "create-hero";
    }

    @GetMapping("/hero/details")
    public String getHeroDetails(Model model, @RequestParam(required = false) String username,
                                 @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        try {
            List<Hero> heroes = new ArrayList<Hero>();
            Pageable paging = PageRequest.of(page - 1, size);

            Page<Hero> pageTuts;
            if (username == null) {
                pageTuts = heroRepository.findAll(paging);
            } else {
                pageTuts = heroRepository.findByNameIgnoreCase(username, paging);
                model.addAttribute("username", username);
            }

            heroes = pageTuts.getContent();

            model.addAttribute("heroes", heroes);
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "details-hero";

    }

    @PostMapping("/hero/details")
    public String listHeroDetails() {
        return "details-hero";
    }

    @GetMapping("/details/{heroClass}")
    public String details(@PathVariable String heroClass) {
        return switch (heroClass) {
            case "warrior" -> "details-warrior";
            case "archer" -> "details-archer";
            case "mage" -> "details-mage";
            default -> "error-page";
        };

    }

    @GetMapping("/hero/create/{heroClass}")
    public String createWarrior(Model model, @PathVariable String heroClass) {
        model.addAttribute("model", new Hero());
        return switch (heroClass) {
            case "warrior" -> "create-warrior";
            case "archer" -> "create-archer";
            case "mage" -> "create-mage";
            default -> "error-page";
        };
    }

    @PostMapping("/hero/create/{spec}")
    public String createdHero(@PathVariable String spec, @Valid HeroDTO heroDTO) {
        switch (spec) {
            case "warrior" -> {
                heroDTO.setHeroClass(ClassEnum.WARRIOR);
                heroDTO.setLevel(1);
            }
            case "archer" -> {
                heroDTO.setHeroClass(ClassEnum.ARCHER);
                heroDTO.setLevel(1);
            }
            case "mage" -> {
                heroDTO.setHeroClass(ClassEnum.MAGE);
                heroDTO.setLevel(1);
            }
        }
        heroService.createHero(heroDTO);
        return "redirect:/hero/details";
    }

    @GetMapping("/hero/delete/{id}")
    public String deleteHero(@PathVariable("id") Long id) {
        heroRepository.deleteById(id);
        return "redirect:/hero/details";
    }

    @GetMapping("/hero/rename/{id}")
    String renameHero(@PathVariable("id") Long id, String name) {
        heroService.renameHero(id, name);
        return "redirect:/hero/details";
    }

    @GetMapping("/hero/train/{id}")
    String trainHero(@PathVariable("id") Long id) {
        heroService.getHero(id);

        return "train-hero";
    }

}