package com.example.khachsan.service;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.entity.User;
import com.example.khachsan.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;


    public List<Booking> findAll() {
        return repository.findAll();
    }

    public void saveUser(Booking booking) {
        repository.save(booking);
    }

    public Booking findById(long id) {
        return repository.findById(id).get();
    }

    public void delete(long id) {
        repository.delete(findById(id));
    }
}
