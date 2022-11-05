package ru.yandex.practicum.filmorate.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

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
    private final Map<Integer, User> userMap = new HashMap<>();

    @GetMapping
    public Collection<User> getAllUsers(){
       // User user2 = new User(1, "ivanJuravlev@mail.ru", "SomeName", "Name",  LocalDate.of(1994, 4, 16));
       // userMap.put(user2.getId(), user2);
        return userMap.values();
    }


    @PostMapping
    public User saveUser(@Valid @RequestBody User user){
        user.setId(userIdCounter++);
        if(userMap.containsKey(user.getId())){
            throw new ValidationException("Пользователь с таким ID уже существует");
        } else {
            if (user.getName() == null || user.getName().isBlank()) {
                user.setName(user.getLogin());
            }
            userMap.put(user.getId(), user);
            log.info("Пользователь с логином {} создан", user.getLogin());

        }
        return user;
    }

    @PutMapping
    public User putUser(@Valid @RequestBody User user){
        if (!userMap.containsKey(user.getId())){
            throw new ValidationException("Такого пользователя не существует, необходимо сначала зарегистрировать" +
                    "пользователя");
        } else {
            userMap.remove(user.getId());
            userMap.put(user.getId(), user);
            log.info("Информация о пользователе с логином {} обновлена", user.getLogin());

        }
        return user;
    }
}
