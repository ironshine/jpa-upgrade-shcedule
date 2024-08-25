package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long userId; // 작성 유저명
    @Column
    private String title; // 할일 제목
    @Column
    private String content; // 할일 내용

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "schedule_id")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<UserSchedule> userList = new ArrayList<>();

    public Schedule(ScheduleSaveRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(ScheduleSaveRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}