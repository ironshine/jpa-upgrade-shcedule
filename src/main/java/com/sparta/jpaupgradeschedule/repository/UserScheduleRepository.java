package com.sparta.jpaupgradeschedule.repository;

import com.sparta.jpaupgradeschedule.entity.Schedule;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
    List<UserSchedule> findAllBySchedule(Schedule schedule);
    List<UserSchedule> findAllByUser(User user);
}
