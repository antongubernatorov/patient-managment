package ru.gubern.authservice.service;

import org.springframework.stereotype.Service;
import ru.gubern.authservice.model.User;
import ru.gubern.authservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
