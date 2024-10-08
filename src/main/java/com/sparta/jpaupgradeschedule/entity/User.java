package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User extends Timestamped{ // 작성일, 수정일
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username; // 유저명
    @Column(nullable = false, unique = true)
    private String email; // 이메일
    @Column
    private String password; // 비밀번호
    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserSchedule> scheduleList = new ArrayList<>();

    public User(UserSaveRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        if (requestDto.getRole()) {
            this.role = UserRoleEnum.ADMIN;
        } else {
            this.role = UserRoleEnum.USER;
        }
    }

    public void update(UserSaveRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
    }
}
