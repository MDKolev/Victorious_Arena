package app.web;

import app.domain.dto.UserLoginDTO;
import app.domain.dto.UserRegistrationDTO;
import app.domain.service.UserServiceModel;
import app.service.UserService;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @GetMapping("/user/register")
    public String register(){
        return "register";
    }

    @PostMapping("/user/register")
    public String registerConfirm(@Valid UserRegistrationDTO userRegistrationDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() ||
                !userRegistrationDTO.getPassword()
                        .equals(userRegistrationDTO.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",bindingResult);

            return "redirect:register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        userRegistrationDTO.setPassword(encodedPassword);

        userService.registerUser(modelMapper.map(userRegistrationDTO, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/user/login")
    public String login(Model model){
        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }

        return "login";
    }

    @PostMapping("/user/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",bindingResult);

            return "redirect:login";
        }

        BCryptPasswordEncoder matchedPassword = new BCryptPasswordEncoder();
        matchedPassword.matches(userLoginDTO.getPassword(), userService.findByPassword(userLoginDTO.getPassword()));


        UserServiceModel userServiceModel = userService
                .findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());


        if(userServiceModel == null){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("isFound",false);

            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userLoginDTO.getUsername());


        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();

        return "redirect:/";
    }
    @ModelAttribute
    public UserRegistrationDTO userRegistrationDTO(){
        return new UserRegistrationDTO();
    }


    @ModelAttribute
    public UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

}
