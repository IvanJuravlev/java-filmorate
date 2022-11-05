package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private int id;


    @NotBlank(message = "email не может быть пустым")
    @Email(message = "не верный формат email")
    private String email;

    @NotBlank(message = "логин не может быть пустым")
    @NotNull(message = "логин не может быть null")

    private String login;

    private String name;

    @NotNull(message = "дата рождения не может быть null")
    @PastOrPresent(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;

}
