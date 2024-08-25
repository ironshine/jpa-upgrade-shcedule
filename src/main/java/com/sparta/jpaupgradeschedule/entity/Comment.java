package com.sparta.jpaupgradeschedule.entity;

import com.sparta.jpaupgradeschedule.dto.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username; // 작성 유저명
    @Column
    private String content; // 댓글 내용

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }
}
