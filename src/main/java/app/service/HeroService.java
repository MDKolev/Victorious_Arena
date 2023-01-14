package app.service;

import app.domain.dto.HeroDTO;
import app.domain.entity.Hero;
import app.domain.service.CreateHeroService;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final UserService userService;

    private final ModelMapper modelMapper;


    public HeroService(HeroRepository heroRepository, UserRepository userRepository, CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void create(CreateHeroService CreateHeroService) {

    }
}
