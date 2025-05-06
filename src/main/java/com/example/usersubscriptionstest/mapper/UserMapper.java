package com.example.usersubscriptionstest.mapper;

import com.example.usersubscriptionstest.dto.UserRequest;
import com.example.usersubscriptionstest.dto.UserResponse;
import com.example.usersubscriptionstest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "id", source = "id")
    UserResponse toResponse(User user);
}