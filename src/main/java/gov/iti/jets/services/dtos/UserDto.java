package gov.iti.jets.services.dtos;

public record UserDto(
    Long id,
    String username,
    String email,
    String phone,
    String city,
    String country,
    String street
) { }