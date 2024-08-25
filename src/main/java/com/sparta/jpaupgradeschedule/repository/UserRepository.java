package com.sparta.jpaupgradeschedule.repository;

import com.sparta.jpaupgradeschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
