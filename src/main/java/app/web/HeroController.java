package app.web;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.service.HeroService;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HeroController {

    private final ModelMapper modelMapper;

    public HeroController(ModelMapper modelMapper, HeroRepository heroRepository, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
    }

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hero/create")
    public String heroCreate() {
        return "create-hero";
    }


  /*  @GetMapping("/hero/details")
    public String getAll(Model model, String username) {
        List<Hero> heroes;
        heroes = username == null ? heroService.findAll() : heroService.findByUsername(username);
        model.addAttribute("heroes", heroes);
        return "details-hero";

    }*/

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
            case "mage" -> "details-mage";
            case "warrior" -> "details-warrior";
            case "archer" -> "details-archer";
            default -> "error-page";
        };

    }


//    @GetMapping("/details/warrior")
//    public String detailsWarrior() {
//        return "details-warrior";
//    }

//    @GetMapping("/details/mage")
//    public String detailsMage() {
//        return "details-mage";
//    }

//    @GetMapping("/details/archer")
//    public String detailsArcher() {
//        return "details-archer";
//    }


    @GetMapping("/hero/create/warrior")
    public String createWarrior(Model model) {
        model.addAttribute("model", new Hero());
//        model.addAttribute(new Hero());
        return "create-warrior";
    }

    @PostMapping("/hero/create/warrior")
    public String createdHero(@Valid HeroDTO heroDTO) {
        heroDTO.setHeroClass(ClassEnum.WARRIOR);
        heroService.createHero(heroDTO);

        return "redirect:/hero/details";
    }

    @GetMapping("/hero/create/archer")
    public String createArcher(Model model) {
        model.addAttribute("model", new Hero());

        return "create-archer";
    }

    @PostMapping("/hero/create/archer")
    public String createdArcher(@Valid HeroDTO heroDTO) {

        heroService.createHero(heroDTO);

        return "redirect:/hero/details";
    }


    @GetMapping("/hero/create/mage")
    public String createMage(Model model) {
        model.addAttribute("model", new Hero());

        return "create-mage";
    }

    @PostMapping("/hero/create/mage")
    public String createdMage(@Valid HeroDTO heroDTO) {

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


/*@GetMapping("/hero/create")
    public String heroCreate(@Valid Hero hero,
                             Model model) {
        model.addAttribute("class", "Create Hero");
        model.addAttribute(new Hero());
        model.addAttribute("Classes", ClassEnum.values());

        heroRepository.save(hero);

        return "create-hero";
    }

    */
// changes

}