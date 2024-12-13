package com.example.khachsan.controller;


import ch.qos.logback.core.model.Model;
import com.example.khachsan.entity.User;
import com.example.khachsan.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public String DangNhap(ModelMap model, @ModelAttribute User users, HttpSession session) {
        User user = service.findByEmail(users.getEmail()); // Tìm người dùng theo email

        if (user != null && user.getPassword().equals(users.getPassword())) {
            session.setAttribute("loggedInUser", user); // Lưu thông tin người dùng vào session
            return "index";
        } else {
            model.addAttribute("message", "Sai Tên Đăng Nhập Hoặc Mật khẩu");
            return "login";
        }
    }


    @GetMapping("/listAccount")
    public String showList(ModelMap model){
        List<User> list = service.findAll();
        model.addAttribute("userList", list);
        return "accountManagement";
    }


    @PostMapping("listAccount/deleteCheckBox")
    public String delete(@RequestParam("idChecked") List<String> idDeletes){

        if(idDeletes != null){
            for(String idDeleteStr : idDeletes){
                int idDelete = Integer.parseInt(idDeleteStr);
                service.delete(idDelete);
            }
        }
        return "redirect:/listAccount";
    }
    @PostMapping("/listAccount/delete")
    public String deleteProduct(@ModelAttribute("id")int id){
        service.delete(id);
        return "redirect:/accountManagement";
    }


    @GetMapping(value = "/returnHome")
    public String showHome() {
        return "redirect:/index";
    }


    @GetMapping(value = "/about")
    public String showAbout() {
        return "about";
    }
    @GetMapping(value = "/blog")
    public String showBlog() {
        return "blog";
    }
    @GetMapping(value = "/rooms")
    public String showRooms() {
        return "rooms";
    }

    @GetMapping(value = "/blogSingle")
    public String showBlogSingle() {
        return "blog-single";
    }

}
