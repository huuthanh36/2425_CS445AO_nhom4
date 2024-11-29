package com.example.khachsan.controller;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.entity.Room;

import com.example.khachsan.entity.User;
import com.example.khachsan.service.BookingService;
import com.example.khachsan.service.RoomService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class RoomController {
    @Autowired
    private BookingService bkService;

    @Autowired
    private RoomService rService;

    @ModelAttribute("bookingList")
    public String showList(){
        List<Booking> list = bkService.findAll();
        return "bookingManagement";
    }

    @ModelAttribute("booking")
    public Booking booking(){
        return new Booking();
    }

    @ModelAttribute("room")
    public Room room(){
        return new Room();
    }


    @GetMapping(value = "/booknow")
    public String showFormBook(ModelMap model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Room> list1 = rService.findAll();
        model.addAttribute("roomList", list1);

        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "booknow";
        } else {
            model.addAttribute("message", "Bạn cần đăng nhập để xem trang này.");
            return "login";
        }
    }
    @PostMapping(value="/booking")
    public String booking(@Valid @ModelAttribute Booking booking, BindingResult bindingResult){

        return "redirect:/index";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, ModelMap model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "profile";
        } else {
            model.addAttribute("message", "Bạn cần đăng nhập để xem trang này.");
            return "login";
        }
    }



}
