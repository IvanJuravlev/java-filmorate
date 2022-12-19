package ru.yandex.practicum.filmorate.controller;//package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ru.yandex.practicum.filmorate.storage.film.InDbFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InDbUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.AssertionsForClassTypes;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import org.assertj.core.api.Assertions;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmStorageTest {

    private final InDbFilmStorage inDbFilmStorage;

    private final InDbUserStorage inDbUserStorage;


    private Film createTestFilm(){
        Film film = Film.builder()
                .name("testFilm")
                .description("desc")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .duration(110)
                .mpa(new Mpa(1, "G"))
                .genres(null)
                .build();
        return film;
    }


   private User createTestUser() {
       User user = User.builder()
               .id(1)
               .email("example@mail.mail")
               .login("login")
               .name("Doe")
               .birthday(LocalDate.of(2000, 12, 22))
               .build();
   return user;
    }



    @Test
    void addFilmTest() {
        inDbFilmStorage.create(createTestFilm());
        AssertionsForClassTypes.assertThat(createTestFilm()).extracting("id").isNotNull();
        AssertionsForClassTypes.assertThat(createTestFilm()).extracting("name").isNotNull();
    }

    @Test
    void updateFilmTest() {
        Film newFilm = createTestFilm();
        inDbFilmStorage.create(newFilm);
        newFilm.setName("testUpdateFilm");
        newFilm.setDescription("testUpdateDesc");
        inDbFilmStorage.update(newFilm);
        AssertionsForClassTypes.assertThat(inDbFilmStorage.getById(newFilm.getId()))
                .hasFieldOrPropertyWithValue("name", "testUpdateFilm")
                .hasFieldOrPropertyWithValue("description", "testUpdateDesc");
    }

    @Test
    void getFilmTest() {
        Film newFilm = createTestFilm();
        inDbFilmStorage.create(newFilm);
        inDbFilmStorage.getById(newFilm.getId());
        AssertionsForClassTypes.assertThat(inDbFilmStorage.getById(newFilm.getId())).hasFieldOrPropertyWithValue("id", newFilm.getId());
    }

    @Test
    void removeFilmTest() {
        Film newFilm = createTestFilm();
        inDbFilmStorage.create(newFilm);
        inDbFilmStorage.delete(newFilm.getId());
        AssertionsForClassTypes.assertThat(newFilm).hasFieldOrPropertyWithValue("id", newFilm.getId());
    }

    @Test
    void updateFilmNotFoundTest() {
        Film filmForUpdate = Film.builder()
                .id(9999)
                .name("testFilm")
                .description(("desc"))
                .releaseDate(LocalDate.of(2020, 1, 1))
                .mpa(new Mpa(1, "G"))
                .genres(null)
                .build();
        Assertions.assertThatThrownBy(() -> inDbFilmStorage.update(filmForUpdate))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    void addLikeFilmTest() {
        Film newFilm = createTestFilm();
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        inDbFilmStorage.create(newFilm);
        System.out.println(newUser.getId() + " - Это юзер Id!");
        System.out.println(newFilm.getId() + " - Это фильм Id!");
        inDbFilmStorage.likeFilm(newFilm.getId(), newUser.getId());
        assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId()).isEmpty());
        assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId())).isNotNull();
        Assertions.assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId()).size() == 2);
    }

    @Test
    void removeFilmLikeTest() {
        Film newFilm = createTestFilm();
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        inDbFilmStorage.create(newFilm);
        inDbFilmStorage.create(newFilm);
        inDbFilmStorage.likeFilm(newFilm.getId(), newUser.getId());
        inDbFilmStorage.deleteLike(newFilm.getId(), newUser.getId());
        assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId()).isEmpty());
        assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId())).isNotNull();
        Assertions.assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId()).size() == 1);
    }

    @Test
    void getBestFilmTest() {
        Film newFilm = createTestFilm();
        Film newFilm2 = createTestFilm();


        inDbFilmStorage.create(createTestFilm());
        inDbFilmStorage.create(newFilm);
        inDbFilmStorage.create(newFilm2);

        User newUser = createTestUser();
        User newUser2 = createTestUser();
        User newUser3 = createTestUser();


        inDbUserStorage.create(newUser);
        inDbUserStorage.create(newUser2);
        inDbUserStorage.create(newUser3);

        inDbFilmStorage.likeFilm(newFilm.getId(), newUser.getId());
        inDbFilmStorage.likeFilm(newFilm.getId(), newUser2.getId());
        inDbFilmStorage.likeFilm(newFilm2.getId(), newUser3.getId());
        inDbFilmStorage.likeFilm(newFilm.getId(), newUser2.getId());
        inDbFilmStorage.likeFilm(newFilm.getId(), newUser3.getId());
        assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId())).isNotNull();
        Assertions.assertThat(inDbFilmStorage.getPopularFilms(newFilm.getId()).size() == 6);
    }
}

