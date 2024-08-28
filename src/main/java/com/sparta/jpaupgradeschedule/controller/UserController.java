package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.LoginRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveResponseDto;
import com.sparta.jpaupgradeschedule.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 저장
    @PostMapping("/users/signup")
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto requestDto, HttpServletResponse res) {
        return ResponseEntity.ok(userService.saveUser(requestDto, res));
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

    // 로그인
    @PostMapping("/users/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "로그인 완료";
    }
}