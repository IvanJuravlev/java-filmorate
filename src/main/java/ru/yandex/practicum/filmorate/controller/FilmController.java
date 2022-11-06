package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {


    private int filmIdCounter = 1;
    @Getter
    private static Map<Integer, Film> filmMap = new HashMap<>();



    @GetMapping
    public Collection<Film> getAllFilms(){
         return filmMap.values();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        film.setId(filmIdCounter++);
             if (FilmValidator.postFilmValidation(film)){
                 filmMap.put(film.getId(), film);
                 log.info("Фильм {} создан", film.getName());
             }
        return film;
    }

    @PutMapping
    public Film putFilm(@Valid @RequestBody Film film){
            if(FilmValidator.putFilmValidation(film)) {
                filmMap.remove(film.getId());
                filmMap.put(film.getId(), film);
                log.info("Информация о фильме {} обновлена", film.getName());
            }
        return film;
    }

}
