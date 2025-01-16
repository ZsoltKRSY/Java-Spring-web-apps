package ro.utcluj.demo.mapper;

import ro.utcluj.demo.dto.UserDto;
import ro.utcluj.demo.model.Role;
import ro.utcluj.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcluj.demo.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserMapper {

    public UserDto userEntityToDto(User user){
        List<String> roles = new ArrayList<>();
        for(Role role : user.getRoles())
            roles.add(role.getRole());

        return UserDto.builder()
                .username(user.getUsername())
                .roles(roles)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users){
        return users.stream()
                .map(this::userEntityToDto)
                .toList();
    }

    public User userDtoToEntity(UserDto userDto, String password, RoleRepository roleRepository){
        List<Role> roles = new ArrayList<>();
        for(String role : userDto.roles())
            roles.add(roleRepository.findByRole(role));

        return User.builder()
                .username(userDto.username())
                .password(password)
                .roles(roles)
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .emailAddress(userDto.emailAddress())
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> userDtos, String password, RoleRepository roleRepository){
        return userDtos.stream()
                .map(userDto -> userDtoToEntity(userDto, password, roleRepository))
                .toList();
    }
}