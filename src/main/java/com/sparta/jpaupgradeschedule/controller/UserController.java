package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveResponseDto;
import com.sparta.jpaupgradeschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 저장
    @PostMapping("/users")
    public UserSaveResponseDto saveUser(@RequestBody UserSaveRequestDto requestDto) {
        return userService.saveUser(requestDto);
    }

    // 단건 조회
    @GetMapping("/users/{id}")
    public UserSaveResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    // 전체 조회
    // 수정
    // 삭제
}
