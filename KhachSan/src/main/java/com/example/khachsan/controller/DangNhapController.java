package com.example.khachsan.controller;


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
@RequestMapping("")
public class DangNhapController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/DangNhap")
    public String showFormDangNhap() {
        return "login";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @PostMapping("/DangNhap")
    public String DangNhap(ModelAndView model,@ModelAttribute User users){
        for (User user : service.findAll()) {
            if(user.getEmail().equals(users.getEmail()) && user.getPassword().equals(users.getPassword())){
            return "index";
            } else {
                model.addObject("message", "Sai Tên Đăng Nhập Hoặc Mật khẩu");
            }
        }
        return "login";
    }

    @GetMapping(value = "/booknow")
    public String showFormbook() {
        return "booknow";
    }

    @GetMapping(value = "/returnHome")
    public String showHome() {
        return "redirect:/DangNhap";
    }
}
