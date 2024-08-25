package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.CommentSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentSaveResponseDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateResponseDto;
import com.sparta.jpaupgradeschedule.entity.Comment;
import com.sparta.jpaupgradeschedule.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(CommentSaveRequestDto requestDto) {
        Comment newComment = new Comment(requestDto.getUsername(), requestDto.getContent());
        Comment saveComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(
                saveComment.getId(),
                saveComment.getUsername(),
                saveComment.getContent(),
                saveComment.getPostTime(),
                saveComment.getUpdateTime()
        );
    }

    public CommentSaveResponseDto getComment(Long id) {
        Comment idComment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("id없음"));
        return new CommentSaveResponseDto(
                idComment.getId(),
                idComment.getUsername(),
                idComment.getContent(),
                idComment.getPostTime(),
                idComment.getUpdateTime()
        );
    }

    public List<CommentSaveResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();

        List<CommentSaveResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentSaveResponseDto dto = new CommentSaveResponseDto(
                    comment.getId(),
                    comment.getUsername(),
                    comment.getContent(),
                    comment.getPostTime(),
                    comment.getUpdateTime()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음"));

        comment.update(requestDto);

        return new CommentUpdateResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContent()
        );
    }

    @Transactional
    public String deleteComment(Long id) {
        commentRepository.delete(commentRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음")));
        return "삭제완료";
    }
}
