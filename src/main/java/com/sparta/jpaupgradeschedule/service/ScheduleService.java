package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.SchedulePageResponseDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        Schedule newSchedule = new Schedule(requestDto);

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

    public Page<SchedulePageResponseDto> getSchedules(int page, int size) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "updateTime");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Schedule> scheduleList = scheduleRepository.findAll(pageable);

        return scheduleList.map(SchedulePageResponseDto::new);
    }

    @Transactional
    public String deleteSchedule(Long id) {
        scheduleRepository.delete(scheduleRepository.findById(id).orElseThrow(() -> new NullPointerException("없는 아이디")));
        return "삭제완료";
    }
}