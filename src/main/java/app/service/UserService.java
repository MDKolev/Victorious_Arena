package app.service;


import app.domain.dto.UserLoginDTO;
import app.domain.dto.UserRegistrationDTO;
import app.domain.entity.User;
import app.domain.service.UserServiceModel;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public UserRegistrationDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);

        return modelMapper.map(userRepository.save(user), UserRegistrationDTO.class);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User loginUser(UserLoginDTO userLoginDTO) {
        return null;
    }
}
