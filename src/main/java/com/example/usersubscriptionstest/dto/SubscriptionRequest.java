package com.example.usersubscriptionstest.dto;

import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest(
        @NotNull(message = "ID платформы обязательно")
        Long platformId
) {}
