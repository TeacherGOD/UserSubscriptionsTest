package com.example.usersubscriptionstest.controller;

import com.example.usersubscriptionstest.dto.PlatformStatsResponse;
import com.example.usersubscriptionstest.entity.Platform;
import com.example.usersubscriptionstest.service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
@Tag(name = "Platforms", description = "API для управления цифровыми сервисами")
public class PlatformController {
    private final PlatformService platformService;

    //тут надо дто создать, но всё же тестовое.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый сервис")
    public Platform createPlatform(@RequestBody Platform platform) {
        return platformService.createPlatform(platform);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сервис по ID")
    public PlatformStatsResponse getPlatform(@PathVariable Long id) {
        return platformService.getByIdWithCount(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить сервис")
    public void deletePlatform(@PathVariable Long id) {
        platformService.deleteById(id);
    }

}