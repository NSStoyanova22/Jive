package com.jive.service;

import com.jive.model.User;
import com.jive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> all() { return repo.findAll(); }
    public void add(String name) { repo.save(new User(name)); }
}