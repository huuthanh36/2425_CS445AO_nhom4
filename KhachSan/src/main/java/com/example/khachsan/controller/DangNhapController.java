package com.example.khachsan.controller;


import ch.qos.logback.core.model.Model;
import com.example.khachsan.entity.User;
import com.example.khachsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/DangNhap")
public class DangNhapController {

    @Autowired
    private UserService service;

    @GetMapping(value = "")
    public String showFormDangNhap() {
        return "DangNhap";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @PostMapping("")
    public String DangNhap(ModelAndView model,@ModelAttribute User users){
        for (User user : service.findAll()) {
            if(user.getEmail().equals(users.getEmail()) && user.getPassword().equals(users.getPassword())){
            return "TrangChu" ;
            } else {
                model.addObject("message", "Sai Tên Đăng Nhập Hoặc Mật khẩu");
            }
        }
        return "DangNhap";
    }

}
