package com.example.usersubscriptionstest.service;

import com.example.usersubscriptionstest.dto.PlatformStatsResponse;
import com.example.usersubscriptionstest.dto.SubscriptionRequest;
import com.example.usersubscriptionstest.dto.SubscriptionResponse;
import com.example.usersubscriptionstest.entity.Platform;
import com.example.usersubscriptionstest.entity.Subscription;
import com.example.usersubscriptionstest.entity.User;
import com.example.usersubscriptionstest.exception.*;
import com.example.usersubscriptionstest.mapper.PlatformStatsMapper;
import com.example.usersubscriptionstest.mapper.SubscriptionMapper;
import com.example.usersubscriptionstest.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final PlatformService platformService;
    private final SubscriptionMapper subscriptionMapper;
    private final PlatformStatsMapper platformStatsMapper;

    @Transactional
    public SubscriptionResponse addSubscription(Long userId, SubscriptionRequest request) {
        User user = userService.findById(userId);
        Platform platform = platformService.getById(request.platformId());

        if (subscriptionRepository.existsByUserIdAndPlatformId(userId, request.platformId())) {
            throw new DuplicateResourceException("Подписка уже существует");
        }

        Subscription subscription = Subscription.builder()
                .user(user)
                .platform(platform)
                .build();

        subscriptionRepository.save(subscription);
        log.info("Добавлена подписка: {} → {}", user.getEmail(), platform.getName());
        return subscriptionMapper.toResponse(subscription);
    }

    public List<SubscriptionResponse> getUserSubscriptions(Long userId) {
        userService.findById(userId);
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(subscriptionMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteSubscription(Long userId, Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Подписка не найдена"));

        if (!subscription.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Доступ запрещен");
        }

        subscriptionRepository.delete(subscription);
        log.info("Удалена подписка ID: {}", subscriptionId);
    }

    public List<PlatformStatsResponse> getTopPlatforms() {
        var top=subscriptionRepository.findTopPlatformsWithSubscribers().stream()
                .map(result -> {
                    Platform platform = (Platform) result[0];
                    Long subscribersCount = (Long) result[1];
                    return platformStatsMapper.toDto(platform, subscribersCount);
                })
                .toList();
        log.info("Найдены топ 3 платформ.");
        return top;
    }
}
