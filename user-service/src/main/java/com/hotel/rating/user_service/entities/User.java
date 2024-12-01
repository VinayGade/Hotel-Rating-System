package com.hotel.rating.user_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    private String userId;

    @Column(name="name", length=45)
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="about")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();   // value of this field isn't stored in db.
}
