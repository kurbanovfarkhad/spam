package com.example.spam.security.services;

import com.example.spam.security.domain.Role;
import com.example.spam.security.domain.User;
import com.example.spam.security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        return userRepository.save(user);
    }
    public User findUser(User user){
        return userRepository.findByUsername(user.getUsername());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public void delete(User user){
        this.userRepository.delete(user);
    }
}
