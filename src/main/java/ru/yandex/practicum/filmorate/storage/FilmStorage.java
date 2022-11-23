package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.Map;

public interface FilmStorage {


   Map<Integer, Film> getAllFilms();
   Collection<Film> findAllFilms();
   Film create(Film film);
   Film update(Film film);
   Film deleteById(int id);
   Film getById(int id);
}
