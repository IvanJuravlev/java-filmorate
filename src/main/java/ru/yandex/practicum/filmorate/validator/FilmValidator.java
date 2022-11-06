package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ValidationException;
import java.time.LocalDate;

public class FilmValidator {

    public static boolean putFilmValidation(Film film) {
        boolean isValid;
        if (!FilmController.getFilmMap().containsKey(film.getId())) {
            isValid = false;
            throw new ValidationException("Фильма с таким ID не существует");
        } else {
            isValid = true;
        }
        return isValid;
    }

    public static boolean postFilmValidation(Film film) {
        boolean isValid;
        if (FilmController.getFilmMap().containsKey(film.getId())) {
            isValid = false;
            throw new ValidationException("Фильм с таким ID уже существует");
        } else if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            isValid = false;
            throw new ValidationException("Дата релиза фильма не может быть раньше даты создания кинематографа");
        } else {
            isValid = true;
        }
        return isValid;
    }
}
