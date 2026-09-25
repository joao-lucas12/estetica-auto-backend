package com.startup.backend.dto;

import com.startup.backend.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        Role role,
        Boolean phoneVerified
) {
}
