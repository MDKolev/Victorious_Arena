package app.service;

import app.domain.dto.HeroDTO;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void createHero(HeroDTO heroDTO) {
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        heroRepository.save(hero);
    }


    public Page<Hero> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return heroRepository.findAll(pageable);
    }

    public void renameHero(Long id, String username) {
        Optional<Hero> byId = heroRepository.findById(id);
        byId.get().setName(username);

    }

    public Hero getHero(Long id) {
        Optional<Hero> byId = heroRepository.findById(id);
       return byId.get();
    }
}