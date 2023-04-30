package app.service;

import app.domain.entity.User;
import app.domain.service.UserServiceModel;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final CurrentUser currentUser = new CurrentUser();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
    private UserService userService;

    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, modelMapper, currentUser, bCryptPasswordEncoder);
    }

    @Test
    void testRegisterUser() {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("testUser");
        userServiceModel.setPassword("testPassword");

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserServiceModel result = userService.registerUser(userServiceModel);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void testFindByUsernameFound() {
        String username = user.getUsername();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserServiceModel result = userService.findByUsername(username);

        assertEquals(user.getId(), result.getId());
        assertEquals(username, result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        verify(userRepository).findByUsername(username);
    }

    @Test
    void testFindByUsernameNotFound() {
        String username = user.getUsername();

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        UserServiceModel result = userService.findByUsername(username);

        assertNull(result);
        verify(userRepository).findByUsername(username);
    }

    @Test
    void testLoginUser() {
        Long id = user.getId();
        String username = user.getUsername();

        userService.loginUser(id, username);

        assertEquals(id, currentUser.getId());
        assertEquals(username, currentUser.getUsername());
    }

    @Test
    void testLogout() {
        userService.logout();

        assertNull(currentUser.getId());
        assertNull(currentUser.getUsername());
    }
}
