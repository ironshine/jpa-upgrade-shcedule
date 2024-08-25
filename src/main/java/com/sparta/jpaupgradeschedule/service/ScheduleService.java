package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.SchedulePageResponseDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.ScheduleSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.entity.UserSchedule;
import com.sparta.jpaupgradeschedule.repository.ScheduleRepository;
import com.sparta.jpaupgradeschedule.repository.UserRepository;
import com.sparta.jpaupgradeschedule.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserScheduleRepository userScheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto, Set<User> user) {
        User idUser = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NullPointerException("없는 아이디"));
        Schedule newSchedule = new Schedule(requestDto);

        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setUser(idUser);
        userSchedule.setSchedule(newSchedule);

        userScheduleRepository.save(userSchedule);

        if(user != null) {
            for (User u : user) {
                if (u.getId().equals(requestDto.getUserId())) {
                    continue;
                }
                userRepository.findById(u.getId()).orElseThrow(() -> new NullPointerException("id 없음"));
                UserSchedule userSchedules = new UserSchedule();
                userSchedules.setUser(u);
                userSchedules.setSchedule(newSchedule);

                userScheduleRepository.save(userSchedules);
            }
        }

        Schedule saveSchedule = scheduleRepository.save(newSchedule);


        return new ScheduleSaveResponseDto(
                saveSchedule.getId(),
                saveSchedule.getUserId(),
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
                idSchedule.getUserId(),
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
                idSchedule.getUserId(),
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