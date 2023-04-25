package app.repository;

import app.domain.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Page<Hero> findByNameIgnoreCase(String username, Pageable pageable);


}
