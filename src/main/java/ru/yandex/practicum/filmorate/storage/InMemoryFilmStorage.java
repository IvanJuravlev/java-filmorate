package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.InternalException;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {



    private final Map<Integer, Film> filmMap = new HashMap<>();

    private int filmId = 1;

    @Override
    public Map<Integer, Film> getAllFilms(){
        return filmMap;
    }
    @Override
    public Collection<Film> findAllFilms(){
        return filmMap.values();
    }

    @Override
    public Film create(Film film) {
        film.setId(filmId++);
        if (postFilmValidation(film)){
            filmMap.put(film.getId(), film);
            log.info("Фильм {} создан", film.getName());
        }
        return film;
    }
    @Override
    public Film update(Film film){
       if (putFilmValidation(film)) {
            filmMap.put(film.getId(), film);
            log.info("Информация о фильме {} обновлена", film.getName());
        }

        return film;
    }

    @Override
    public Film getById(int id){
        return filmMap.get(id);
    }

    @Override
    public Film deleteById(int id){
        Film film = filmMap.get(id);
        filmMap.remove(id);
        return film;
    }


    public boolean putFilmValidation(Film film) {
        boolean isValid;
        if (!filmMap.containsKey(film.getId())) {
            isValid = false;
            throw new ObjectNotFoundException("Фильма с таким ID не существует");
        } else {
            isValid = true;
        }
        return isValid;
    }



    public boolean postFilmValidation(Film film) {
        boolean isValid;
        if (filmMap.containsKey(film.getId())) {
            isValid = false;
            throw new InternalException("Фильм с таким ID уже существует");
        } else if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            isValid = false;
            throw new ValidationException("Дата релиза фильма не может быть раньше даты создания кинематографа");
        } else {
            isValid = true;
        }
        return isValid;
    }

}
