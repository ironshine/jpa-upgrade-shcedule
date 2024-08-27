package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.CommentSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentSaveResponseDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateResponseDto;
import com.sparta.jpaupgradeschedule.entity.Comment;
import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.repository.CommentRepository;
import com.sparta.jpaupgradeschedule.repository.ScheduleRepository;
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
    private final ScheduleService scheduleService;

    @Transactional
    public CommentSaveResponseDto saveComment(Long id, CommentSaveRequestDto requestDto) {
        Schedule schedule = scheduleService.scheduleFindById(id);
        Comment newComment = new Comment(requestDto.getUsername(), requestDto.getContent());
        schedule.getCommentList().add(newComment);
        Comment saveComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(saveComment);
    }

    public CommentSaveResponseDto getComment(Long id) {
        Comment idComment = commentFindById(id);
        return new CommentSaveResponseDto(idComment);
    }

    public List<CommentSaveResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();

        List<CommentSaveResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentSaveResponseDto dto = new CommentSaveResponseDto(comment);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = commentFindById(id);

        comment.update(requestDto);

        return new CommentUpdateResponseDto(comment);
    }

    @Transactional
    public String deleteComment(Long id) {
        commentRepository.delete(commentFindById(id));
        return "삭제완료";
    }

    public Comment commentFindById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음"));
    }
}
