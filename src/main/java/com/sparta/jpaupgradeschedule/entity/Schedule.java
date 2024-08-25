package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username; // 작성 유저명
    @Column
    private String title; // 할일 제목
    @Column
    private String content; // 할일 내용

    @OneToOne(mappedBy = "schedule")
    private Comment comment;

    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public void update(ScheduleSaveRequestDto requestDto) {
        this.username= requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
