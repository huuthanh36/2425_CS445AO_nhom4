package com.example.khachsan.repository;

import com.example.khachsan.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("SELECT b FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    Double findTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    Long countBookingsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
