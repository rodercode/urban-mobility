package com.example.urbanmobility.dto;
import jakarta.persistence.Column;
public record AccountDto(
        String username,
        String role,
        String email,
        String phone
) {

}
