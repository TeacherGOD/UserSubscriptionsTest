package com.example.usersubscriptionstest.mapper;

import com.example.usersubscriptionstest.dto.SubscriptionResponse;
import com.example.usersubscriptionstest.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(target = "platformName", source = "platform.name")
    SubscriptionResponse toResponse(Subscription subscription);
}