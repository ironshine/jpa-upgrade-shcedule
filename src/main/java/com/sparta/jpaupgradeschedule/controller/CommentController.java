package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.CommentSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentSaveResponseDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateRequestDto;
import com.sparta.jpaupgradeschedule.dto.CommentUpdateResponseDto;
import com.sparta.jpaupgradeschedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping("/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@RequestParam("schedule") Long id, @RequestBody CommentSaveRequestDto requestDto) {
        return ResponseEntity.ok(commentService.saveComment(id, requestDto));
    }

    // 댓글 단건 조회
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentSaveResponseDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    // 댓글 전체 조회
    @GetMapping("/comments")
    public ResponseEntity<List<CommentSaveResponseDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    // 댓글 수정
    @PutMapping("/comments/update/{id}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(id,requestDto));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
