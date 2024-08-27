package com.sparta.jpaupgradeschedule.dto;

import com.sparta.jpaupgradeschedule.entity.Comment;
import lombok.Getter;

@Getter
public class CommentUpdateResponseDto {
    private final Long id;
    private final String username;
    private final String content;

    public CommentUpdateResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
    }
}
