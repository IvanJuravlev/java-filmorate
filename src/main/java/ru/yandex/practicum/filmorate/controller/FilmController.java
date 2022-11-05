package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

   // private static final LocalDate DATE = LocalDate.of(1895, 12, 28);
    //Валидацию по дате

    private int filmIdCounter = 1;
    private Map<Integer, Film> filmMap = new HashMap<>();


    @GetMapping
    public Collection<Film> getAllFilms(){

      //  Film film2 = new Film(1,"ivanJurav", "SomeName", LocalDate.of(1994, 4, 16), 50);
       //  filmMap.put(film2.getId(), film2);
         return filmMap.values();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        film.setId(filmIdCounter++);
        if (filmMap.containsKey(film.getId())) {
            throw new ValidationException("Фильм с таким ID уже существует");
        } else if(film.getReleaseDate().isBefore(LocalDate.of(1895,12,28))){
            throw new ValidationException("Дата релиза фильма не может быть раньше даты создания кинематографа");
        } else {
            filmMap.put(film.getId(), film);
            log.info("Фильм {} создан", film.getName());


        }
        return film;
    }

    @PutMapping
    public Film putFilm(@Valid @RequestBody Film film){
        if(!filmMap.containsKey(film.getId())){
            throw new ValidationException("Фильма с таким ID не существует");
        } else {
            filmMap.remove(film.getId());
            filmMap.put(film.getId(), film);
            log.info("Информация о фильме {} обновлена", film.getName());


    }
        return film;



     //Валидацию по дате
    }



}
