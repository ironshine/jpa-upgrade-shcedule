package com.sparta.jpaupgradeschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Timestamp 쓰기위해 넣음
@SpringBootApplication
public class JpaUpgradeScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaUpgradeScheduleApplication.class, args);
    }

}
