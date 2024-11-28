package com.example.khachsan.controller;

import com.example.khachsan.entity.User;
import com.example.khachsan.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DangKyController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/register")
    public String showFormDangKy() {
        return "register";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @PostMapping(value = "/register")
    public String dangNhap(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        user.validate(user,bindingResult);
        if (bindingResult.hasErrors()){
            return "register";
        }
        service.saveUser(user);
        return "index";
    }



}
