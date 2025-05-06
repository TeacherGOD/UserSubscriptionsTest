package com.example.usersubscriptionstest.repository;

import com.example.usersubscriptionstest.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    boolean existsByName(String name);
}
