package com.example.khachsan.controller;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/api/bookings")
    public List<Booking> getBookingsBetweenDates(@RequestParam("startDate") String startDate,
                                                 @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return bookingService.getBookingsBetweenDates(start, end);
    }

    @GetMapping("/api/revenue")
    public Double getTotalRevenueBetweenDates(@RequestParam("startDate") String startDate,
                                              @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return bookingService.getTotalRevenueBetweenDates(start, end);
    }

    @GetMapping("/api/count")
    public Long countBookingsBetweenDates(@RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return bookingService.countBookingsBetweenDates(start, end);
    }

    @GetMapping("/api/weeklyRevenueLast10Weeks")
    public List<Double> getWeeklyRevenueForLast10Weeks() {
        return bookingService.getWeeklyRevenueForLast4Weeks();
    }

}
