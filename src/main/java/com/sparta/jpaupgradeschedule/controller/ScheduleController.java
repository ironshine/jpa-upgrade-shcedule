package com.sparta.jpaupgradeschedule.controller;

import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveResponseDto;
import com.sparta.jpaupgradeschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 저장
    @PostMapping("/schedules")
    public ScheduleSaveResponseDto saveSchedule(@RequestBody ScheduleSaveRequestDto requestDto) {
        return scheduleService.saveSchedule(requestDto);
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{id}")
    public ScheduleSaveResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 일정 수정
    @PutMapping("/schedules/update/{id}")
    public ScheduleSaveResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleSaveRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }
}
