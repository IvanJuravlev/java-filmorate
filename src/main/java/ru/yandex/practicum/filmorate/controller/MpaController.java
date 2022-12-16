package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mpa")
public class MpaController {

    private final MpaService mpaService;

    @GetMapping
    public List<Mpa> getAll(){
        return mpaService.getAll();
    }

    @GetMapping("/{id}")
    public Mpa getById(@PathVariable int id){
        return mpaService.getById(id);
    }


}
