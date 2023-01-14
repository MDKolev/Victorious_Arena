package app.seeder;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import app.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeroSeeder implements CommandLineRunner {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroSeeder(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.heroRepository.count() == 0) {
            List<Hero> heroes = Arrays.stream(ClassEnum.values())
                    .map(c -> new Hero())
                    .collect(Collectors.toList());

            this.heroRepository.saveAll(heroes);
        }




    }
}
