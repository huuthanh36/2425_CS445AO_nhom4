package com.example.khachsan.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        return "contact"; // Tên của template HTML
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestParam("name") String name,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("email") String email,
                                    @RequestParam("message") String message,
                                    Model model) {

        // Tạo email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("tranphuocthao122@gmail.com"); // Địa chỉ email nhận
        mailMessage.setSubject("Thông tin liên hệ từ " + name);
        mailMessage.setText("Tên: " + name + "\nSố điện thoại: " + phone + "\nEmail: " + email + "\nThông điệp: " + message);

        // Gửi email
        mailSender.send(mailMessage);

        model.addAttribute("success", "Thông tin liên hệ đã được gửi thành công!");
        return "contact"; // Tên của template HTML
    }
}
