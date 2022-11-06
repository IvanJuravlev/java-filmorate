package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ValidationException;

public class UserValidator {

    public static boolean postUserValidation(User user) {
        boolean isValid;
        if (UserController.getUserMap().containsKey(user.getId())) {
            isValid = false;
            throw new ValidationException("Пользователь с таким ID уже существует");
        } else {
            isValid = true;
            if (user.getName() == null || user.getName().isBlank()) {
                user.setName(user.getLogin());
            }
            return isValid;
        }
    }
    public static boolean putUserValidation(User user){
        boolean isValid;
        if (!UserController.getUserMap().containsKey(user.getId())){
            isValid = false;
            throw new ValidationException("Такого пользователя не существует, необходимо сначала зарегистрировать" +
                    "пользователя");
        } else {
            isValid = true;
        } return isValid;
    }
}
