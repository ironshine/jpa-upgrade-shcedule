package com.sparta.jpaupgradeschedule.repository;

import com.sparta.jpaupgradeschedule.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
