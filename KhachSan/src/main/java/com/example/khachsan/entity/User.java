package com.example.khachsan.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @Pattern(regexp="^(0|\\+84)[3|5|7|8|9][0-9]{8}$", message="Số điện thoại không hợp lệ")
    @NotEmpty
    private String phone;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<Booking> bookkings ;

    public User(Long userId, String email, String password, String phone, String fullName, String address, String role, LocalDateTime createdAt, LocalDateTime updatedAt, Set<Booking> bookkings) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bookkings = bookkings;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Booking> getBookkings() {
        return bookkings;
    }

    public void setBookkings(Set<Booking> bookkings) {
        this.bookkings = bookkings;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Validate username
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "email is required");

        // Validate password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required");

        // Validate email
        if (user.getEmail() == null || !user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            errors.rejectValue("email", "field.invalid", "Invalid email format");
        }




    }










}

//@NotNull – kiểm tra giá trị null
//@AssertTrue – kiểm tra giá trị thuộc tính là true
//@Size – kiểm tra độ dài min and max
//@Min – kiểm tra giá trị nhỏ nhất
//@Max – Kiểm tra giá trị lớn nhất
//@Email – kiểm tra email có hợp lệ
//@NotEmpty – kiểm tra không được trống và empty
//@NotBlank – kiểm tra giá trị không được null hoặc khoảng trắng
//@Positive and @PositiveOrZero – kiểm tra chỉ được phép là số nguyên dương từ 0 trở đi
//@Negative and @NegativeOrZero – kiểm tra số âm
//@Past and @PastOrPresent – kiểm tra ngày từ quá khứ đến hiện tại.
//@Future and @FutureOrPresent – kiểm tra ngày từ hiện tại đến tương lai
