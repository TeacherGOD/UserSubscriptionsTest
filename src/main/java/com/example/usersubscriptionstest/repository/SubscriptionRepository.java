package com.example.usersubscriptionstest.repository;

import com.example.usersubscriptionstest.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    boolean existsByUserIdAndPlatformId(Long userId, Long platformId);



    @Query("SELECT s.platform, COUNT(s) as subscribersCount " +
            "FROM Subscription s " +
            "GROUP BY s.platform " +
            "ORDER BY subscribersCount DESC LIMIT 3")
    List<Object[]> findTopPlatformsWithSubscribers();

    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.platform.id = :id")
    Long countByPlatformId(Long id);
}
