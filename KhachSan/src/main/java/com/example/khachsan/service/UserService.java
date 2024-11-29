package com.example.khachsan.service;

import com.example.khachsan.entity.User;
import com.example.khachsan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User findById(long id) {
        return repository.findById(id).get();
    }

    public void delete(long id) {
        repository.delete(findById(id));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
