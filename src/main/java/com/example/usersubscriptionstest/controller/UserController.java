package com.example.usersubscriptionstest.controller;

import com.example.usersubscriptionstest.dto.UserRequest;
import com.example.usersubscriptionstest.dto.UserResponse;
import com.example.usersubscriptionstest.entity.User;
import com.example.usersubscriptionstest.mapper.UserMapper;
import com.example.usersubscriptionstest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать пользователя")
    @ApiResponse(responseCode = "201", description = "Пользователь создан")
    @ApiResponse(responseCode = "409", description = "Конфликт данных")
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        log.info("Запрос на создание: {}", request.email());
        User user = userService.createUser(request);
        return userMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    public UserResponse getUser(
            @PathVariable
            @Parameter(description = "ID пользователя", example = "1") Long id
    ) {
        log.info("Запрос пользователя с ID: {}", id);
        return userMapper.toResponse(userService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные пользователя")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {
        log.info("Обновление пользователя с ID: {}", id);
        User updatedUser = userService.updateUser(id, request);
        return userMapper.toResponse(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить пользователя")
    public void deleteUser(@PathVariable Long id) {
        log.info("Удаление пользователя с ID: {}", id);
        userService.deleteById(id);
    }
}