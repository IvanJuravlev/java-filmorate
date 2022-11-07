package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Film {

    int id;

    @NotNull(message = "название фильма не может быть null")
    @NotBlank(message = "название фильма не может быть пустым")
    String name;

    @NotNull(message = "описание фильма не может быть пустым")
    @Size(max = 200, message = "описание фильма не может быть больше 200 символов")
    String description;

    @NotNull(message = "дата выхода фильма не может быть null")
    LocalDate releaseDate;

    @NotNull(message = "длительность фильма не может быть null")
    @Positive(message = "длительность фильма не может быть отрицательным числом")
    int duration;
}
