package com.example.spam.security.db;

import com.example.spam.security.domain.Role;
import com.example.spam.security.domain.User;
import com.example.spam.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;

    public DbInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User fara = new User("DG",new BCryptPasswordEncoder().encode("1"),"@mail.ru","Farkhad","Kurbanov",true,Collections.singleton(Role.ADMIN));
        this.userRepository.save(fara);
    }
}
