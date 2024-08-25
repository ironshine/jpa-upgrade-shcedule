package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveResponseDto;
import com.sparta.jpaupgradeschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 저장
    @PostMapping("/users")
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto requestDto) {
        return ResponseEntity.ok(userService.saveUser(requestDto));
    }

    // 단건 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<UserSaveResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserSaveResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // 수정
    @PutMapping("/users/update/{id}")
    public ResponseEntity<UserSaveResponseDto> updateUser(@PathVariable Long id, @RequestBody UserSaveRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    // 삭제
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}