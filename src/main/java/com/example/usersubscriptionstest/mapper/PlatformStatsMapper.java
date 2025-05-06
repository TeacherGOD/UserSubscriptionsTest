package com.example.usersubscriptionstest.mapper;

import com.example.usersubscriptionstest.dto.PlatformStatsResponse;
import com.example.usersubscriptionstest.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformStatsMapper {
    @Mapping(target = "name", source = "platform.name")
    @Mapping(target = "url", source = "platform.url")
    @Mapping(target = "price", source = "platform.price")
    @Mapping(target = "subscribersCount", source = "subscribersCount")
    PlatformStatsResponse toDto(Platform platform, Long subscribersCount);
}
