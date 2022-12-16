package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;
import ru.yandex.practicum.filmorate.model.Genre;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreStorage genreStorage;

    public List<Genre> findAll() {
        return genreStorage.getAll();
    }

    public Genre getById(int id) {
        return genreStorage.getById(id);
    }
}
