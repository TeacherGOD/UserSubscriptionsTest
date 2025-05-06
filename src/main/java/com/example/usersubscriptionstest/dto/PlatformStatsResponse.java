package com.example.usersubscriptionstest.dto;

public record PlatformStatsResponse(
        String name,
        String url,
        Double price,
        Long subscribersCount
) {}
