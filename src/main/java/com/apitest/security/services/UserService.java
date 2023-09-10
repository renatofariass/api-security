package com.apitest.security.services;

import com.apitest.security.domain.user.RegisterDto;
import com.apitest.security.domain.user.User;
import com.apitest.security.domain.user.UserRole;
import com.apitest.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void insert(RegisterDto data) {
        String bCryptPassword = passwordEncoder.encode(data.password());
        User user  = new User(data.login(), bCryptPassword, UserRole.USER);
        repository.save(user);
    }

    public UserDetails findByUserLogin(String login) {
        return repository.findByLogin(login);
    }
}
