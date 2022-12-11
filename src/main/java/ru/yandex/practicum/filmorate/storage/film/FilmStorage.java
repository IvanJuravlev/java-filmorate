package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FilmStorage {
   List<Film> getAllFilms();
   Film getById(int id);

  // Collection<Film> findAll();

   Film create (Film film);
   Film update (Film film);
   Film delete(int id);
   List<Film> getPopularFilms(int count);

   Film likeFilm(int filmId, int userId);
   Film deleteLike(int filmId, int userId);

}
