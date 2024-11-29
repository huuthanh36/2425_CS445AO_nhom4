package com.example.khachsan.controller;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.entity.Room;

import com.example.khachsan.service.BookingService;
import com.example.khachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showFormBook(ModelMap model) {
        List<Room> list1 = rService.findAll();
        model.addAttribute("roomList", list1);
        return "booknow";
    }

}
