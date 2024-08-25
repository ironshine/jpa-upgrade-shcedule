package com.sparta.jpaupgradeschedule.dto;

import com.sparta.jpaupgradeschedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SchedulePageResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime postTime;
    private LocalDateTime updateTime;
    private int commentCount;

    public SchedulePageResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUserId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.postTime = schedule.getPostTime();
        this.updateTime = schedule.getUpdateTime();
        this.commentCount = schedule.getCommentList().size();
    }
}