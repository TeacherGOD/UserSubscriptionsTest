package com.example.usersubscriptionstest.service;

import com.example.usersubscriptionstest.dto.PlatformStatsResponse;
import com.example.usersubscriptionstest.entity.Platform;
import com.example.usersubscriptionstest.exception.DuplicateResourceException;
import com.example.usersubscriptionstest.exception.ResourceNotFoundException;
import com.example.usersubscriptionstest.mapper.PlatformStatsMapper;
import com.example.usersubscriptionstest.repository.PlatformRepository;
import com.example.usersubscriptionstest.repository.SubscriptionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformStatsMapper platformStatsMapper;
    private final SubscriptionRepository subscriptionRepository;

    //Очевидно, что это только для примера.
    @PostConstruct
    public void initDefaultPlatforms() {
        List<Platform> defaultPlatforms = List.of(
                Platform.builder()
                        .name("YouTube Premium")
                        .url("https://www.youtube.com/premium")
                        .price(9999.0)
                        .build(),
                Platform.builder()
                        .name("Netflix")
                        .url("https://www.netflix.com")
                        .price(1873.0)
                        .build(),
                Platform.builder()
                        .name("VK Музыка")
                        .url("https://music.vk.com/")
                        .price(199.0)
                        .build(),
                Platform.builder()
                        .name("Яндекс Плюс")
                        .url("https://plus.yandex.ru/")
                        .price(399.0)
                        .build()
        );

        defaultPlatforms.forEach(platform -> {
            if (!platformRepository.existsByName(platform.getName())) {
                platformRepository.save(platform);
                log.info("Создан тестовый сервис: {}", platform.getName());
            }
        });
    }

    public Platform createPlatform(Platform platform) {
        if (platformRepository.existsByName(platform.getName())) {
            throw new DuplicateResourceException("Сервис с таким названием уже существует");
        }
        return platformRepository.save(platform);
    }

    public Platform getById(Long id) {
        return platformRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Сервис не найден"));
    }

    public PlatformStatsResponse getByIdWithCount(Long id) {
        Platform platform =platformRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Сервис не найден"));
        Long countSub = subscriptionRepository.countByPlatformId(id);
        log.info("Запрос информации о платформе: {}", platform.getName());
        return platformStatsMapper.toDto(platform,countSub);
    }

    public void deleteById(Long id) {
        if (!platformRepository.existsById(id)) {
            throw new ResourceNotFoundException("Сервис не найден");
        }
        platformRepository.deleteById(id);
        log.info("Сервис с ID {} удален", id);
    }
}
