package com.sparta.jpaupgradeschedule.dto;

import com.sparta.jpaupgradeschedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSaveResponseDto {
    private final Long id;
    private final String username;
    private final String content;
    private final LocalDateTime postTime;
    private final LocalDateTime updateTime;

    public CommentSaveResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.postTime = comment.getPostTime();
        this.updateTime = comment.getUpdateTime();
    }
}
