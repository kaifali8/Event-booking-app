package com.example.event_book.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String phone;
    private String address;
    private String gender;
    private LocalDate dob;

    public UserDTO(long id,String name ,String username, String email,String photo,String phone,String address,String gender,LocalDate dob) {
        this.id=id;
        this.username=username;
        this.name=name;
        this.email=email;
        this.photo=photo;
        this.phone=phone;
        this.address=address;
        this.gender=gender;
        this.dob=dob;
    }
}
