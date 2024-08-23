package com.sparta.jpaupgradeschedule.repository;

import com.sparta.jpaupgradeschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    List<Schedule> findAllByPostTimeBetweenOrderByUpdateTimeDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
