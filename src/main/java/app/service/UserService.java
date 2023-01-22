package app.service;


import app.domain.entity.User;
import app.domain.service.UserServiceModel;
import app.repository.UserRepository;
import app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

      public UserServiceModel findByUsernameAndPassword(String username, String password) {
          return userRepository.findByUsernameAndPassword(username, password)
                  .map(user -> modelMapper.map(user, UserServiceModel.class))
                  .orElse(null);

      }

    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    public void logout() {
        currentUser.setId(0);
        currentUser.setUsername(null);
    }


    public String findByPassword(String password) {
        return userRepository.findByPassword(password).toString();
    }
}