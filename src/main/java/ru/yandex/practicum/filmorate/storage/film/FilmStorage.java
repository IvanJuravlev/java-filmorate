package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;
import java.util.List;

public interface FilmStorage {
   List<Film> getAllFilms();
   Film getById(int id);

   Film create (Film film);
   Film update (Film film);
   Film delete(int id);
   List<Film> getPopularFilms(int count);

   Film likeFilm(int filmId, int userId);
   Film deleteLike(int filmId, int userId);

}
