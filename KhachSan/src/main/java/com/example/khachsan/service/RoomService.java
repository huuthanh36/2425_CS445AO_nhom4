package com.example.khachsan.service;

import com.example.khachsan.entity.Room;
import com.example.khachsan.entity.User;
import com.example.khachsan.repository.RoomRepository;
import com.example.khachsan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;


    public List<Room> findAll() {
        return repository.findAll();
    }

    public void saveUser(Room room) {
        repository.save(room);
    }

    public Room findById(long id) {
        return repository.findById(id).get();
    }
    public void delete(long id) {
        repository.delete(findById(id));
    }


}
