package ro.utcluj.demo.service;

import ro.utcluj.demo.dto.UserDto;
import ro.utcluj.demo.model.RegistrationRequest;
import ro.utcluj.demo.model.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);
}