package com.example.khachsan.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomTypeId;
    private String roomTypeName;
    private double price;

    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms = new HashSet<>();
    // Getters and Setters

    // Getters and Setters

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomType(Long roomTypeId, String roomTypeName, double price, Set<Room> rooms) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.price = price;
        this.rooms = rooms;
    }

    public RoomType(String roomTypeName, double price, Set<Room> rooms) {
        this.roomTypeName = roomTypeName;
        this.price = price;
        this.rooms = rooms;
    }



    public RoomType() {
    }
}

