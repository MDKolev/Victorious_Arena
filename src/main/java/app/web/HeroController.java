package app.web;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.service.HeroService;
import app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/hero/details")
    public String heroDetails() {
        return "details-hero";

    }

    @GetMapping("/details/warrior")
    public String detailsWarrior() {
        return "details-warrior";
    }

    @GetMapping("/details/mage")
    public String detailsMage() {
        return "details-mage";
    }

    @GetMapping("/details/archer")
    public String detailsArcher() {
        return "details-archer";
    }

    @GetMapping("/hero/create/warrior")
    public String createWarrior(@Valid Hero hero,
                                Model model) {
        model.addAttribute(new Hero());

        heroRepository.save(hero);

        return "create-warrior";
    }

    @GetMapping("/hero/create/archer")
    public String createArcher(@Valid Hero hero,
                                Model model) {
        model.addAttribute(new Hero());

        heroRepository.save(hero);

        return "create-archer";
    }

    @GetMapping("/hero/create/mage")
    public String createMage(@Valid Hero hero,
                                Model model) {
        model.addAttribute(new Hero());

        heroRepository.save(hero);

        return "create-mage";
    }
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

    @PostMapping("/hero/create")
    public String createdHero(@ModelAttribute("") @Valid HeroDTO heroDTO) {

        heroService.createHero(heroDTO);

        return "details-hero";
    }*/