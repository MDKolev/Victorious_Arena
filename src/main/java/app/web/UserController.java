package app.web;

import app.domain.dto.UserLoginDTO;
import app.domain.dto.UserRegistrationDTO;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/user/register")
    public String register(Model model) {
        UserRegistrationDTO registerUser = new UserRegistrationDTO();
        model.addAttribute("user", registerUser);

        return "register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDTO userRegistrationDTO) {
        userService.registerUser(userRegistrationDTO);

        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        UserLoginDTO loginUser = new UserLoginDTO();
        model.addAttribute("user", loginUser);

        return "login";
    }

    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute("user") @Valid UserLoginDTO userLoginDTO) {
        userService.loginUser(userLoginDTO);

        return "redirect:/home";
    }
}
