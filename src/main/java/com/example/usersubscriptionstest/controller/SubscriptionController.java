package com.example.usersubscriptionstest.controller;

import com.example.usersubscriptionstest.dto.PlatformStatsResponse;
import com.example.usersubscriptionstest.dto.SubscriptionRequest;
import com.example.usersubscriptionstest.dto.SubscriptionResponse;
import com.example.usersubscriptionstest.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Subscriptions", description = "API для управления подписками")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{userId}/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить подписку пользователю")
    public SubscriptionResponse addSubscription(
            @PathVariable Long userId,
            @RequestBody SubscriptionRequest request
    ) {
        return subscriptionService.addSubscription(userId, request);
    }

    @GetMapping("/users/{userId}/subscriptions")
    @Operation(summary = "Получить подписки пользователя")
    public List<SubscriptionResponse> getSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить подписку")
    public void deleteSubscription(
            @PathVariable Long userId,
            @PathVariable Long subscriptionId
    ) {
        subscriptionService.deleteSubscription(userId, subscriptionId);
    }

    @GetMapping("/subscriptions/top")
    @Operation(summary = "ТОП-3 популярных подписок", description = "Показываются только те Платформы, в которых более 0 подписчиков.")
    public List<PlatformStatsResponse> getTopSubscriptions() {
        return subscriptionService.getTopPlatforms();
    }
}
