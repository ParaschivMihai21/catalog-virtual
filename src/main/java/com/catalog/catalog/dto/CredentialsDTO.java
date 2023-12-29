package com.catalog.catalog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CredentialsDTO {

    @NotEmpty(message = "Username is required")
    String userName;

    @NotEmpty
    @Size(min = 3, message = "password should have at least 3 characters")
    String password;
}
