package com.catalog.catalog.service;

import com.catalog.catalog.entity.Role;
import com.catalog.catalog.repository.RoleRepository;
import com.catalog.catalog.dto.CredentialsDTO;
import com.catalog.catalog.dto.SignUpDTO;
import com.catalog.catalog.dto.UserDTO;
import com.catalog.catalog.entity.User;
import com.catalog.catalog.exceptions.AppException;
import com.catalog.catalog.mappers.UserMapper;
import com.catalog.catalog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public UserDTO login(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByUserName(credentialsDTO.getUserName())
                .orElseThrow(() -> new AppException("Unknown user"));
        if (passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())) {
            return userMapper.toUserDTO(user);

        }
        throw new AppException(("Invalid password"));

    }


    public UserDTO register(SignUpDTO signUpDTO) {
        Optional<User> newUser = userRepository.findByUserName(signUpDTO.getUserName());
        if (newUser.isPresent()) {
            throw new AppException("User already exists");
        }
        Role role = roleRepository.findByRoleName(signUpDTO.getRoleName());
        User user = userMapper.signUpToUser(signUpDTO);

        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }
}