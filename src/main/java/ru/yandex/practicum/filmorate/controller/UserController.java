package ru.yandex.practicum.filmorate.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private static final String ID = "/{id}";

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User put(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping(ID)
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @DeleteMapping(ID)
    public User deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @PutMapping(ID + "/friends/{friendId}")
    public User addFriend(@PathVariable int id, @PathVariable int friendId) {
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping(ID + "/friends/{friendId}")
    public User removeFriend(@PathVariable int id, @PathVariable int friendId) {
        return userService.removeFriend(id, friendId);
    }

    @GetMapping(ID + "/friends")
    public List<User> getFriendsList(@PathVariable int id) {
        return userService.getFriendsList(id);
    }

    @GetMapping(ID + "/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return userService.getManualFriends(id, otherId);
    }
}
