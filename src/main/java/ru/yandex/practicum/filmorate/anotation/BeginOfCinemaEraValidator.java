package ru.yandex.practicum.filmorate.anotation;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;
import java.time.LocalDate;

public class BeginOfCinemaEraValidator implements ConstraintValidator<BeginOfCinemaEra, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isBefore(LocalDate.of(1895, 12, 28));
    }
}
