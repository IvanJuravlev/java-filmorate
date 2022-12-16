package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {


    private final FilmStorage filmStorage;

    public List<Film> getAllFilms(){
        return filmStorage.getAllFilms();
    }

    public Film create(Film film){
        return filmStorage.create(film);
    }

    public Film update(Film film){
        return filmStorage.update(film);
    }

    public Film getById(int id){
        return filmStorage.getById(id);
    }

    public Film delete(int id){
        return filmStorage.delete(id);
    }

        public Film deleteLike(int filmId, int userId){
        return filmStorage.deleteLike(filmId, userId);
    }


    public Film likeFilm(int filmId, int userId){
        return filmStorage.likeFilm(filmId, userId);
    }


    public List<Film> getPopularFilms(int count) {
        return filmStorage.getPopularFilms(count);
    }

}

