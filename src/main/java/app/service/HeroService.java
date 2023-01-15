package app.service;

import app.domain.dto.HeroDTO;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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


    public HeroDTO createHero(HeroDTO heroDTO) {
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        return modelMapper.map(heroRepository.save(hero), HeroDTO.class);
    }
}