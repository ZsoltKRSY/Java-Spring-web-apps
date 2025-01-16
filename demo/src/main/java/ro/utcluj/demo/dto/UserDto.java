package ro.utcluj.demo.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record UserDto(
        String username,
        List<String> roles,
        String firstName,
        String lastName,
        String emailAddress) {}