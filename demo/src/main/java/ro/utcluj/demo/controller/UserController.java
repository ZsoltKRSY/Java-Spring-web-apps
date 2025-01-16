package ro.utcluj.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.utcluj.demo.dto.UserDto;
import ro.utcluj.demo.model.RegistrationRequest;
import ro.utcluj.demo.service.RoleService;
import ro.utcluj.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/add-user")
    public String addUser(@RequestParam(value="addUserSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Add new User");
        model.addAttribute("addUserSuccess", success);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new RegistrationRequest());

        return "add-user";
    }

    @PostMapping("users/createUser")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes){
        System.out.println(registrationRequest);
        UserDto userDto = userService.registerUser(registrationRequest);

        if(userDto != null) {
            redirectAttributes.addAttribute("addUserSuccess", "Success");
        }
        else{
            redirectAttributes.addAttribute("addUserSuccess", "Failed");
        }

        return "redirect:/add-user";
    }

    @PostMapping("/delete-user/{username}")
    public String deleteUserByUsername(@PathVariable (required = true) String username){
        userService.deleteUserByUsername(username);

        return "redirect:/users";
    }

}