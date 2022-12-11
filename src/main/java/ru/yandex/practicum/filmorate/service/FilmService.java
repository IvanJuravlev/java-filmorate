package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {


    private final FilmStorage filmStorage;

    public Collection<Film> findAllFilms(){
        log.info("Список всех фильмов");
        return filmStorage.findAllFilms();
    }

    public Film create(Film film){
        return filmStorage.create(film);
    }

    public Film update(Film film){
        return filmStorage.update(film);
    }

    public Film getById(int id){
        if (!filmStorage.getAllFilms().containsKey(id)){
            throw new ObjectNotFoundException("Фильм с таким Id не найден");
        }
        log.info("Найден фильм с id {}", id);
        return filmStorage.getById(id);
    }

    public Film deleteById(int id){
        if (!filmStorage.getAllFilms().containsKey(id)){
            throw new ObjectNotFoundException("Фильм стаким Id не найден, удаление невозможно");
        }
        log.info("Фильм с id {} удалён", id);
        return filmStorage.deleteById(id);
    }

        public Film removeLike(int filmId, int userId){
        if (!filmStorage.getAllFilms().containsKey(filmId)){
            throw new ObjectNotFoundException("Фильм с таким Id не найден");
        }
        if (!filmStorage.getById(filmId).getUsersLikes().contains(userId)) {
            throw new ObjectNotFoundException("Лайк от пользователя отсутствует");
        }
        filmStorage.getById(filmId).getUsersLikes().remove(userId);
        log.info("Пользователь {} удалили лайк к фильму {}", userId, filmId);
        return filmStorage.getById(filmId);
    }


    public Film addLike(int filmId, int userId){
        if (!filmStorage.getAllFilms().containsKey(filmId)){
            throw new ObjectNotFoundException("Фильм с таким Id не найден");
        }
        filmStorage.getById(filmId).getUsersLikes().add(userId);
        log.info("Пользователь {} поставил лайк фильму {}", userId, filmId);
        return filmStorage.getById(filmId);
    }



    public List<Film> getBestFilms(int count) {
        log.info("Список популярных фильмов отправлен");

        return filmStorage.findAllFilms().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getUsersLikes().size(), o1.getUsersLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }

}
