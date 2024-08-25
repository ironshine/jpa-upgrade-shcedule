package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username; // 작성 유저명
    @Column
    private String content; // 댓글 내용

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }
}
