package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


//@Data
//public class User {
//
//    private Set<Integer> friends = new HashSet<>();
//
//    @PositiveOrZero
//    private int id;
//    @NotBlank(message = "Отсутствует email")
//    @Email(message = "Некорректный email")
//    @Email
//    private final String email;
//    @NotNull(message = "Отсутствует логин")
//    @Pattern(regexp = "\\S+", message = "Логин содержит пробелы")
//    private final String login;
//    private String name;
//    @NotNull(message = "Не указана дата рождения")
//    @PastOrPresent(message = "Некорректная дата рождения")
//    private final LocalDate birthday;

@Data
//@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User {

     Set<Integer> friends = new HashSet<>();

    int id;

    @NotBlank(message = "email не может быть пустым")
    @Email(message = "не верный формат email")
    String email;

    @NotBlank(message = "логин не может быть пустым")
    @NotNull(message = "логин не может быть null")

    String login;

    String name;

    @NotNull(message = "дата рождения не может быть null")
    @PastOrPresent(message = "дата рождения не может быть в будущем")
    LocalDate birthday;

}
