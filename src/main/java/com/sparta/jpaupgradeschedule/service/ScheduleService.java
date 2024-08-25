package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        Schedule newSchedule = new Schedule(requestDto.getUsername(), requestDto.getTitle(), requestDto.getContent());

        Schedule saveSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleSaveResponseDto(
                saveSchedule.getId(),
                saveSchedule.getUsername(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getPostTime(),
                saveSchedule.getUpdateTime()
        );
    }

    public ScheduleSaveResponseDto getSchedule(Long id) {
        Schedule idSchedule = scheduleRepository.findById(id).orElseThrow(() -> new NullPointerException("없는 아이디"));
        return new ScheduleSaveResponseDto(
                idSchedule.getId(),
                idSchedule.getUsername(),
                idSchedule.getTitle(),
                idSchedule.getContent(),
                idSchedule.getPostTime(),
                idSchedule.getUpdateTime()
        );
    }

    @Transactional
    public ScheduleSaveResponseDto updateSchedule(Long id, ScheduleSaveRequestDto requestDto) {
        Schedule idSchedule = scheduleRepository.findById(id).orElseThrow(() -> new NullPointerException("없는 아이디"));
        idSchedule.update(requestDto);

        return new ScheduleSaveResponseDto(
                idSchedule.getId(),
                idSchedule.getUsername(),
                idSchedule.getTitle(),
                idSchedule.getContent(),
                idSchedule.getPostTime(),
                idSchedule.getUpdateTime()
        );
    }
}