package com.example.khachsan.controller;

import com.example.khachsan.entity.Booking;
import com.example.khachsan.entity.Room;
import com.example.khachsan.entity.User;
import com.example.khachsan.service.BookingService;
import com.example.khachsan.service.RoomService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("")
public class BookingController {

    @Autowired
    private BookingService bkService;

    @Autowired
    private RoomService rService;

    @ModelAttribute("room")
    public Room room(){
        return new Room();
    }

    @ModelAttribute("booking")
    public Booking booking(){
        return new Booking();
    }

    @GetMapping(value = "/booknow")
    public String showFormBook(@ModelAttribute Room room,@ModelAttribute Booking booking,ModelMap model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Room> list1 = rService.findAll();
        model.addAttribute("roomList", list1);

        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "booknow";
        } else {
            model.addAttribute("message", "Bạn cần đăng nhập để xem trang này.");
            return "login";
        }
    }
    @PostMapping(value="/booknow")
    public String booking(@Valid @ModelAttribute Booking booking, BindingResult bindingResult, ModelMap model, HttpSession session) {
        // Kiểm tra null cho checkInDate và checkOutDate
        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            bindingResult.rejectValue("checkInDate", "error.booking", "Ngày nhận phòng và ngày trả phòng là bắt buộc.");
            bindingResult.rejectValue("checkOutDate", "error.booking", "Ngày nhận phòng và ngày trả phòng là bắt buộc.");
            model.addAttribute("roomList",rService.findAll());
            return "booknow"; // Trả về trang đặt phòng nếu thiếu thông tin
        }

        // Đặt ngày đặt phòng hiện tại
        booking.setBookingDate(LocalDate.now());

        // Tính toán tổng giá tiền dựa trên số ngày và giá phòng
        long daysBetween = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        booking.setTotalPrice(daysBetween * booking.getRoom().getPrice());

        // Xác thực booking
        booking.validate(booking, bindingResult);

        // Lấy thông tin người dùng từ session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);

        // Kiểm tra lỗi xác thực
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Thêm thông tin booking vào model
        model.addAttribute("booking", booking);
        return "pay";
    }

}
