package app.web;

import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HeroController {

    private CurrentUser currentUser;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hero/create")
    public String heroCreate(@ModelAttribute
                                 @Valid Hero hero,
                                        Model model) {
        model.addAttribute("class", "Create Hero");
        model.addAttribute(new Hero());
        model.addAttribute("Classes", ClassEnum.values());

        heroRepository.save(hero);

        return "create-hero";

    }

    @PostMapping("/hero/create")
    @ResponseBody
    public  String createdHero(@RequestParam  String name,
                              @RequestParam  ClassEnum heroClass,
                              @RequestParam  int level) {


               return "redirect:/hero/details";
    }





    @GetMapping("/hero/details")
    public String heroDetails() {
        return "details-hero";

    }
}
