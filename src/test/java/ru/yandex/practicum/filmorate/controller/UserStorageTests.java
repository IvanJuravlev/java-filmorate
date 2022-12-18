package ru.yandex.practicum.filmorate.controller;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.AssertionsForClassTypes;
import ru.yandex.practicum.filmorate.model.User;
import org.assertj.core.api.Assertions;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.storage.user.InDbUserStorage;

import java.util.Collection;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserStorageTests {

    private final InDbUserStorage inDbUserStorage;

    private User createTestUser (){
        User user = User.builder()
                .email("example@mail.mail")
                .login("login")
                .name("Doe")
                .birthday(LocalDate.of(2000, 12, 22))
                .build();
        return user;
    }

    @Test
    void addUserTest() {
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        AssertionsForClassTypes.assertThat(newUser).extracting("id").isNotNull();
        AssertionsForClassTypes.assertThat(newUser).extracting("name").isNotNull();
    }



    @Test
    void findUserByIdTest() {
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        AssertionsForClassTypes.assertThat(inDbUserStorage.getById(newUser.getId())).hasFieldOrPropertyWithValue("id", newUser.getId());
    }

    @Test
    void updateUserByIdTest() {
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        newUser.setName("testUpdatedName");
        newUser.setLogin("testUpdatedLogin");
        newUser.setEmail("updatedExample@mail.mail");
        inDbUserStorage.update(newUser);
        AssertionsForClassTypes.assertThat(inDbUserStorage.getById(newUser.getId()))
                .hasFieldOrPropertyWithValue("login", "testUpdatedLogin")
                .hasFieldOrPropertyWithValue("name", "testUpdatedName")
                .hasFieldOrPropertyWithValue("email", "updatedExample@mail.mail");
    }

    @Test
    public void testUpdateUserNotFound() {
        User user = User.builder()
                .id(9999)
                .login("testName")
                .email("example@mail.mail")
                .birthday(LocalDate.of(2000, 12, 22))
                .build();
        Assertions.assertThatThrownBy(() -> inDbUserStorage.update(user))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    void addFriendshipTest() {
        User newUser = createTestUser();
        User newUser2 = createTestUser();


        inDbUserStorage.create(newUser);
        inDbUserStorage.create(newUser2);
        assertThat(inDbUserStorage.getFriends(newUser.getId()).isEmpty());
        inDbUserStorage.addFriend(newUser.getId(), newUser2.getId());
        assertThat(inDbUserStorage.getFriends(newUser.getId())).isNotNull();
        Assertions.assertThat(inDbUserStorage.getFriends(newUser.getId()).size() == 2);
    }

    @Test
    void removeFriendshipTest() {
        User newUser = createTestUser();
        User newUser2 = createTestUser();


        inDbUserStorage.create(newUser);
        inDbUserStorage.create(newUser2);
        assertThat(inDbUserStorage.getFriends(newUser.getId()).isEmpty());
        inDbUserStorage.addFriend(newUser.getId(), newUser2.getId());
        assertThat(inDbUserStorage.getFriends(newUser.getId())).isNotNull();
        Assertions.assertThat(inDbUserStorage.getFriends(newUser.getId()).size() == 2);
        inDbUserStorage.deleteFriend(newUser.getId(), newUser2.getId());
        Assertions.assertThat(inDbUserStorage.getFriends(newUser.getId()).size() == 1);
    }

    @Test
    void getFriendshipTest() {
        User newUser = createTestUser();
        User newUser2 = createTestUser();

        inDbUserStorage.create(newUser);
        inDbUserStorage.create(newUser2);
        assertThat(inDbUserStorage.getFriends(newUser.getId()).isEmpty());
        inDbUserStorage.addFriend(newUser.getId(), newUser2.getId());
        Assertions.assertThat(inDbUserStorage.getFriends(newUser.getId()).size() == 2);
    }


    @Test
    void getCommonFriendshipTest() {
        User newUser = createTestUser();
        User newUser2 = createTestUser();
        User newUser3 = createTestUser();

        inDbUserStorage.create(newUser);
        inDbUserStorage.create(newUser2);
        inDbUserStorage.create(newUser3);
        inDbUserStorage.addFriend(newUser.getId(), newUser3.getId());
        inDbUserStorage.addFriend(newUser2.getId(), newUser3.getId());
        Assertions.assertThat(inDbUserStorage.getCommonFriends(newUser.getId(), newUser2.getId()).size() == 1);
    }

    @Test
    void getAllUsersTest() {
        User newUser = createTestUser();

        inDbUserStorage.create(newUser);
        Collection<User> users = inDbUserStorage.getAllUsers();
        Assertions.assertThat(users).isNotEmpty().isNotNull().doesNotHaveDuplicates();
        Assertions.assertThat(users).extracting("email").contains(newUser.getEmail());
        Assertions.assertThat(users).extracting("login").contains(newUser.getLogin());
    }

    @Test
    void removeUserByIdTest() {
        User newUser = createTestUser();


        inDbUserStorage.create(newUser);
        inDbUserStorage.delete(newUser.getId());
        Assertions.assertThatThrownBy(()->inDbUserStorage.getById(newUser.getId()))
                .isInstanceOf(ObjectNotFoundException.class);
    }
}
