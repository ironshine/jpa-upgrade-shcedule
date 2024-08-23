package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; // 작성 유저명
    private String title; // 할일 제목
    private String content; // 할일 내용

    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public void setUpdateSchedule(ScheduleSaveRequestDto requestDto) {
        this.username= requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
