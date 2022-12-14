package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User {

    @PositiveOrZero
    int id;

    @NotBlank(message = "email не может быть пустым")
    @Email(message = "не верный формат email")
    String email;

    @NotBlank(message = "логин не может быть пустым")
    @NotNull(message = "логин не может быть null")
    String login;

    @NotNull
    String name;

    @NotNull(message = "дата рождения не может быть null")
    @PastOrPresent(message = "дата рождения не может быть в будущем")
    LocalDate birthday;

}
