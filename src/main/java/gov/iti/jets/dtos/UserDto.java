package gov.iti.jets.dtos;

public record UserDto(
    Long id,
    String username,
    String email,
    String phone,
    String city,
    String country,
    String street
) { }