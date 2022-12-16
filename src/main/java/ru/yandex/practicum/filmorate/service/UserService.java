package ru.yandex.practicum.filmorate.service;


//import ru.yandex.practicum.filmorate.storage.user.UserStorage;
//import ru.yandex.practicum.filmorate.model.User;
//import org.springframework.stereotype.Service;
//import lombok.RequiredArgsConstructor;
//import java.util.Collection;
//import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserStorage userStorage;
//
//    public Collection<User> findAll() {
//        return userStorage.findAll();
//    }
//
//    public User create(User user) {
//        validate(user);
//        return userStorage.create(user);
//    }
//
//    public User update(User user) {
//        validate(user);
//        return userStorage.update(user);
//    }
//
//    public User getById(int id) {
//        return userStorage.getById(id);
//    }
//
//    public User deleteById(int id) {
//        return userStorage.deleteById(id);
//
//    }
//
//    public List<Integer> addFriendship(int firstId, int secondId) {
//        return userStorage.addFriendship(firstId, secondId);
//    }
//
//    public List<Integer> removeFriendship(int firstId, int secondId) {
//        return userStorage.removeFriendship(firstId, secondId);
//    }
//
//    public List<User> getFriendsListById(int id) {
//        return userStorage.getFriendsListById(id);
//    }
//
//    public List<User> getCommonFriendsList(int firstId, int secondId) {
//        return userStorage.getCommonFriendsList(firstId, secondId);
//    }
//
//    private void validate(User user) {
//        if (user.getName() == null || user.getName().isBlank()) user.setName(user.getLogin());
//    }
//}


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

     private final UserStorage userStorage;

     public List<User> getAllUsers(){
         return userStorage.getAllUsers();
     }

     public User create(User user){
         setUserName(user);
         return userStorage.create(user);
     }

     public User update(User user){
         setUserName(user);
         return userStorage.update(user);
     }

     public User getById(int id){
         return userStorage.getById(id);
     }

     public User delete(int id){
         return userStorage.delete(id);
     }


     public void addFriend(int firstId, int secondId){
         userStorage.addFriend(firstId, secondId);

     }

     public void deleteFriend(int firstId, int secondId){
         userStorage.deleteFriend(firstId, secondId);
     }

     public List<User> getCommonFriends(int firstId, int secondId){
           return userStorage.getCommonFriends(firstId, secondId);
     }

     public List<User> getFriendsList(int id){
         return userStorage.getFriends(id);
     }

    private void setUserName(User user) {
        if (user.getName() == null || user.getName().isBlank()) user.setName(user.getLogin());
    }



}
