package com.example.usersubscriptionstest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime endDate = LocalDateTime.now()
            .with(TemporalAdjusters.lastDayOfMonth())
            .withHour(23)
            .withMinute(59)
            .withSecond(59)
            .withNano(999_999_999);
}
