package com.startup.backend.service;

import com.startup.backend.dto.UserCreateRequest;
import com.startup.backend.dto.UserResponse;
import com.startup.backend.entity.User;
import com.startup.backend.enums.Role;
import com.startup.backend.exception.EmailAlreadyExistsException;
import com.startup.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.startup.backend.exception.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setPassword(request.password());
        user.setRole(Role.CLIENT);
        user.setPhoneVerified(false);

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id " + id));
        return toResponse(user);
    }

    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("e-mail " + email));
        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getPhoneVerified()
        );
    }


}
