package com.example.usersubscriptionstest.service;

import com.example.usersubscriptionstest.dto.UserRequest;
import com.example.usersubscriptionstest.entity.User;
import com.example.usersubscriptionstest.exception.DuplicateResourceException;
import com.example.usersubscriptionstest.exception.ResourceNotFoundException;
import com.example.usersubscriptionstest.mapper.UserMapper;
import com.example.usersubscriptionstest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserRequest request)
    {
        if (userRepository.existsByName(request.name())) {
            throw new DuplicateResourceException("Имя уже занято");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email уже занят");
        }
        User user = userMapper.toEntity(request);

        log.info("Создан пользователь: {}", user.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRequest request)
    {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if (userRepository.existsByEmailOrNameAndIdNot(request.name(), request.email(), id)) {
            throw new DuplicateResourceException("Имя или email уже заняты");
        }


        existingUser.setName(request.name());
        existingUser.setEmail(request.email());
        existingUser.setPassword(request.password());
        User resUser=userRepository.save(existingUser);
        log.info("Обновлен пользователь с ID: {}", id);
        return resUser;
    }

    public User findById(Long id)
    {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        log.info("Пользователь найден. id: {}",id);
        return user;
    }

    public void deleteById(Long id)
    {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        userRepository.deleteById(id);
        log.info("Пользователь с id {} удалён",id);
    }

}