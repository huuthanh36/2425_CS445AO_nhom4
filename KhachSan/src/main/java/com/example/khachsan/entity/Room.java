package com.example.khachsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;
    private String status;
    private Double price;

    @JsonBackReference
    @OneToMany(mappedBy = "room")
    private Set<Booking> bookkings ;
    // Getters and Setters


    public Room() {
    }

    public Room(Long roomId, String roomName, String status, Double price, Set<Booking> bookkings) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.status = status;
        this.price = price;
        this.bookkings = bookkings;
    }

    public Room(String roomName, String status, Double price, Set<Booking> bookkings) {
        this.roomName = roomName;
        this.status = status;
        this.price = price;
        this.bookkings = bookkings;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Booking> getBookkings() {
        return bookkings;
    }

    public void setBookkings(Set<Booking> bookkings) {
        this.bookkings = bookkings;
    }
}
