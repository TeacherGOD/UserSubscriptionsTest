package com.example.usersubscriptionstest.repository;

import com.example.usersubscriptionstest.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(@NotBlank(message = "Email обязателен") @Email(message = "Некорректный email") String email);

    boolean existsByEmailOrNameAndIdNot(String email, String name, Long id);

    boolean existsByName(@NotBlank(message = "Имя обязательно") String name);
}
