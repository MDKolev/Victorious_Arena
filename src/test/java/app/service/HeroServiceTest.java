package app.service;

import app.domain.dto.HeroDTO;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrentUser currentUser;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HeroService heroService;

    @Mock
    private Hero hero;
    private HeroDTO heroDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        heroService = new HeroService(heroRepository, userRepository, currentUser, userService, modelMapper);
    }

    @Test
    public void testCreateHero() {
        Hero hero = modelMapper.map(heroDTO, Hero.class);

        when(heroRepository.save(hero)).thenReturn(hero);

        heroService.createHero(heroDTO);

        verify(heroRepository, times(1)).save(hero);
    }

    @Test
    public void testGetHero() {
        Optional<Hero> optionalHero = Optional.of(hero);

        when(heroRepository.findById(hero.getId())).thenReturn(optionalHero);

        Hero result = heroService.getHero(hero.getId());

        verify(heroRepository, times(1)).findById(hero.getId());

        assertThat(result).isEqualTo(hero);
    }
}
