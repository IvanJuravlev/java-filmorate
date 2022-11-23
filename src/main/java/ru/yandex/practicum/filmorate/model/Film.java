package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.anotation.BeginOfCinemaEra;

import javax.validation.constraints.*;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Film {

    Set<Integer> usersLikes = new HashSet<>();

    @PositiveOrZero
    int id;

    @NotNull(message = "название фильма не может быть null")
    @NotBlank(message = "название фильма не может быть пустым")
    String name;

    @NotNull(message = "описание фильма не может быть пустым")
    @Size(max = 200, message = "описание фильма не может быть больше 200 символов")
    String description;

    @NotNull(message = "дата выхода фильма не может быть null")
    @BeginOfCinemaEra
    LocalDate releaseDate;

    @NotNull(message = "длительность фильма не может быть null")
    @Positive(message = "длительность фильма не может быть отрицательным числом")
    int duration;
}
