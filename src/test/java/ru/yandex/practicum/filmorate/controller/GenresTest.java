package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;
import ru.yandex.practicum.filmorate.storage.genre.InDbGenreStorage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenresTest {

    private final InDbGenreStorage genre;

    @Test
    public void testGetAllGenres() {
        List<Genre> genreStorage = genre.getAll();
        System.out.println(genreStorage);
        Assertions.assertThat(genreStorage)
                .extracting(Genre::getName)
                .containsAll(Arrays.asList("Комедия", "Драма", "Мультфильм", "Триллер", "Документальный", "Боевик"));
    }

    @Test
    public void testGetGenreById() {
        Genre genre1 = genre.getById(2);
        Assertions.assertThat(genre1)
                .hasFieldOrPropertyWithValue("id", 2)
                .hasFieldOrPropertyWithValue("name", "Драма");
    }
}
