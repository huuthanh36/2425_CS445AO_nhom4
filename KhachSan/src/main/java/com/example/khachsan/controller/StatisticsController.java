package com.example.khachsan.controller;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private BookingService bookingService;




    @GetMapping("/statistics")
    public String showStatistics() {
            return "statistics";
    }


}
