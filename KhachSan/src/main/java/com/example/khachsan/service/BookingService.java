package com.example.khachsan.service;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.entity.User;
import com.example.khachsan.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;


    public List<Booking> findAll() {
        return repository.findAll();
    }

    public void saveBooking(Booking booking) {
        repository.save(booking);
    }

    public Booking findById(long id) {
        return repository.findById(id).get();
    }

    public void delete(long id) {
        repository.delete(findById(id));
    }

    public List<Booking> getBookingsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findBookingsBetweenDates(startDate, endDate);
    }
    public Double getTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findTotalRevenueBetweenDates(startDate, endDate);
    }
    public Long countBookingsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countBookingsBetweenDates(startDate, endDate);
    }

    public List<Double> getWeeklyRevenueForLast4Weeks() {
        List<Double> weeklyRevenue = new ArrayList<>();
        LocalDateTime endDate = LocalDateTime.now();

        for (int i = 0; i < 4; i++) {
            LocalDateTime startDate = endDate.minusWeeks(1);
            Double revenue = repository.findTotalRevenueBetweenDates(startDate, endDate);
            weeklyRevenue.add(revenue);
            endDate = startDate;
        }

        return weeklyRevenue;
    }



}
