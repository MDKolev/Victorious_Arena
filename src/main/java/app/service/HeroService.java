package app.service;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.domain.service.CreateHeroService;
import app.domain.service.HeroServiceModel;
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


    public void createHero(CreateHeroService createHeroService) {
        Hero hero = modelMapper.map(createHeroService, Hero.class);
        modelMapper.map(heroRepository.save(hero), HeroServiceModel.class);
    }

    public Hero findByClass(ClassEnum classEnum) {
        return heroRepository.findByName(classEnum).orElse(null);
    }
}