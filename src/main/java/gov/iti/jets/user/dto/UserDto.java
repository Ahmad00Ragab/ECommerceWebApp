package gov.iti.jets.user.dto;

public record UserDto(
    String username,
    String email,
    String password,
    String phone,
    String city,
    String country,
    String street
) { }