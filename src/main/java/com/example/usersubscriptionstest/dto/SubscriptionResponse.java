package com.example.usersubscriptionstest.dto;

import java.time.LocalDateTime;

public record SubscriptionResponse(
        Long id,
        String platformName,
        LocalDateTime startDate,
        LocalDateTime endDate
) {}
