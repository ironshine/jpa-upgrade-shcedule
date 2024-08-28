package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.ScheduleGetIdResponseDto;
import com.sparta.jpaupgradeschedule.dto.SchedulePageResponseDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 저장
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(
            @RequestBody ScheduleSaveRequestDto requestDto,
            @RequestParam(value = "user", required = false) Set<User> user) {
        return ResponseEntity.ok(scheduleService.saveSchedule(requestDto, user));
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleGetIdResponseDto> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // 일정 수정
    @PutMapping("/schedules/update/{id}")
    public ResponseEntity<ScheduleSaveResponseDto> updateSchedule
    (@PathVariable Long id, @RequestBody ScheduleSaveRequestDto requestDto, HttpServletResponse res, HttpServletRequest req) throws IOException {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto, res, req));
    }

    // 일정을 Spring Data JPA 의 Pageable 과 Page 인터페이스를 활용하여 페이지네이션을 구현
    @GetMapping("/schedules")
    public ResponseEntity<Page<SchedulePageResponseDto>> getSchedules(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(scheduleService.getSchedules(page - 1, size));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/delete/{id}")
    public String deleteSchedule(@PathVariable Long id, HttpServletResponse res, HttpServletRequest req) throws IOException{
        return scheduleService.deleteSchedule(id, res, req);
    }
}
