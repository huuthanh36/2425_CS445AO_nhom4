package com.example.khachsan.entity;

import jakarta.persistence.*;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;
    private String status;

    @ManyToOne
    @JoinColumn(name = "roomTypeId")
    private RoomType roomType;

    // Getters and Setters

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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Room() {
    }

    public Room(Long roomId, String roomName, String status, RoomType roomType) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.status = status;
        this.roomType = roomType;
    }
}
