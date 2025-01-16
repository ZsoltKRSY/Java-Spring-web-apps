package ro.utcluj.demo.service;

import ro.utcluj.demo.dto.UserDto;
import ro.utcluj.demo.mapper.RoleMapper;
import ro.utcluj.demo.mapper.UserMapper;
import ro.utcluj.demo.model.RegistrationRequest;
import ro.utcluj.demo.model.Role;
import ro.utcluj.demo.model.User;
import ro.utcluj.demo.repository.RoleRepository;
import ro.utcluj.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleRepository.findByRole(registrationRequest.getRole()));

        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .roles(userRoles)
                .build();
        return this.createUser(user);
    }

    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id){
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userMapper.userEntityToDto(userRepository.save(user));
        }
        catch(Exception ex){
            return null;
        }
    }

    public UserDto updateUser(User user){
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public void deleteUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
            System.out.println(user);
        user.ifPresent(userRepository::delete);
    }
}