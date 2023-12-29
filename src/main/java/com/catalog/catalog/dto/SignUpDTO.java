package com.catalog.catalog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    @NotEmpty(message = "Username is required")
    private String userName;

    @NotEmpty
    @Size(min = 3, message = "password should have at least 3 characters")
    private String password;

    @NotEmpty(message = "Role name is required")
    private String roleName;
}
