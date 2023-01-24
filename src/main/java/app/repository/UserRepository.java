package app.repository;

import app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User>findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String username);


    User findByPassword(String password);

    Optional<User> findByUsername(String username);
}
 //     @Query("SELECT username, password FROM projectmyheroes.user")