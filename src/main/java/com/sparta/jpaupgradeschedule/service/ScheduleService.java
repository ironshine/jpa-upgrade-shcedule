package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.*;
import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.entity.UserRoleEnum;
import com.sparta.jpaupgradeschedule.entity.UserSchedule;
import com.sparta.jpaupgradeschedule.jwt.JwtUtil;
import com.sparta.jpaupgradeschedule.repository.ScheduleRepository;
import com.sparta.jpaupgradeschedule.repository.UserRepository;
import com.sparta.jpaupgradeschedule.repository.UserScheduleRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserScheduleRepository userScheduleRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto, Set<User> user) {
        User idUser = userService.userFindById(requestDto.getUserId());
        Schedule newSchedule = new Schedule(requestDto);

        saveUserSchedule(idUser, newSchedule);

        if(user != null) {
            for (User u : user) {
                if (!u.getId().equals(requestDto.getUserId())) {
                    userService.userFindById(u.getId());
                    saveUserSchedule(u,newSchedule);
                }
            }
        }
        Schedule saveSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleSaveResponseDto(saveSchedule);
    }

    public ScheduleGetIdResponseDto getSchedule(Long id) {
        Schedule idSchedule = scheduleFindById(id);

        List<UserSchedule> userScheduleList = userScheduleRepository.findAllBySchedule(idSchedule);
        List<UserResponseDto> userList = new ArrayList<>();

        for (UserSchedule userSchedule : userScheduleList) {
            userList.add(new UserResponseDto(userSchedule.getUser()));
        }

        return new ScheduleGetIdResponseDto(idSchedule, userList);
    }

    @Transactional
    public ScheduleSaveResponseDto updateSchedule(Long id, ScheduleSaveRequestDto requestDto, HttpServletResponse res, HttpServletRequest req) throws IOException {
        Claims info = jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(jwtUtil.getTokenFromRequest(req)));
        if (userRepository.findByEmail(info.getSubject()).getRole().equals(UserRoleEnum.ADMIN)) {
            Schedule idSchedule = scheduleFindById(id);
            idSchedule.update(requestDto);

            return new ScheduleSaveResponseDto(idSchedule);
        } else {
            res.sendError(403, "403, role is not ADMIN");
            return null;
        }
    }

    public Page<SchedulePageResponseDto> getSchedules(int page, int size) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "updateTime");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Schedule> scheduleList = scheduleRepository.findAll(pageable);

        return scheduleList.map(SchedulePageResponseDto::new);
    }

    @Transactional
    public String deleteSchedule(Long id, HttpServletResponse res, HttpServletRequest req) throws IOException {
        Claims info = jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(jwtUtil.getTokenFromRequest(req)));
        if (userRepository.findByEmail(info.getSubject()).getRole().equals(UserRoleEnum.ADMIN)) {
            List<UserSchedule> userScheduleList = userScheduleRepository.findAllBySchedule(scheduleFindById(id));
            userScheduleRepository.deleteAll(userScheduleList);
            scheduleRepository.delete(scheduleFindById(id));
            return "삭제완료";
        } else {
            res.sendError(403, "role is not ADMIN");
            return "role is not ADMIN";
        }
    }

    public Schedule scheduleFindById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new NullPointerException("없는 아이디"));
    }
    public void saveUserSchedule(User user, Schedule schedule){
        UserSchedule userSchedules = new UserSchedule();
        userSchedules.setUser(user);
        userSchedules.setSchedule(schedule);
        userScheduleRepository.save(userSchedules);
    }
}