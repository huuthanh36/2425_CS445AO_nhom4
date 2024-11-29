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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @GetMapping("/listBooking")
    public String showList(ModelMap model){
        List<Booking> list = bkService.findAll();
        model.addAttribute("bookingList", list);
        return "bookingManagement";


    }

    @PostMapping("/listBooking/deleteCheckBox")
    public String delete(@RequestParam("idChecked") List<String> idDeletes){

        if(idDeletes != null){
            for(String idDeleteStr : idDeletes){
                int idDelete = Integer.parseInt(idDeleteStr);
                bkService.delete(idDelete);
            }
        }
        return "redirect:/listAccount";
    }








}
