package ru.yandex.practicum.filmorate.controller;


import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private int userIdCounter = 1;
    @Getter
    private static Map<Integer, User> userMap = new HashMap<>();

    @GetMapping
    public Collection<User> getAllUsers(){
        return userMap.values();
    }


    @PostMapping
    public User saveUser(@Valid @RequestBody User user){
        user.setId(userIdCounter++);
        if (UserValidator.postUserValidation(user)){
            userMap.put(user.getId(), user);
            log.info("Пользователь с логином {} создан", user.getLogin());
        }
        return user;
    }

    @PutMapping
    public User putUser(@Valid @RequestBody User user){
        if (UserValidator.putUserValidation(user)){
            userMap.remove(user.getId());
            userMap.put(user.getId(), user);
            log.info("Информация о пользователе с логином {} обновлена", user.getLogin());
        }
        return user;
    }
}
