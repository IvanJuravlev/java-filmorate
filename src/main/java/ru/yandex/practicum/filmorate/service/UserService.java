package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.InternalException;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

     private final UserStorage userStorage;

     public Collection<User> findAllUsers(){
         return userStorage.findAllUsers();
     }

     public User create(User user){
         return userStorage.create(user);
     }

     public User update(User user){
         return userStorage.update(user);
     }

     public User getById(int id){
         if(!userStorage.getAllUsers().containsKey(id)){
             throw new ObjectNotFoundException("Пользователя с такм Id не зарегистрирован");
         }
         log.info("Найден пользователь с ID {}", id);
         return userStorage.getById(id);
     }

     public User deleteById(int id){
         if(!userStorage.getAllUsers().containsKey(id)){
             throw new ObjectNotFoundException("Пользователя с такм Id не зарегистрирован");
         }
         log.info("Пользователь с Id {} удалён", id);
         return userStorage.deleteById(id);
     }


     public User addFriend(int firstId, int secondId){
         if (!userStorage.getAllUsers().containsKey(firstId) || !userStorage.getAllUsers().containsKey(secondId)){
             throw new ObjectNotFoundException("Пользователей с таким Id не существует");
         }
         if (userStorage.getById(firstId).getFriends().contains(secondId)){
             throw new InternalException("Пользователи уже являются друзьями");
         }
         userStorage.getById(firstId).getFriends().add(secondId);
         userStorage.getById(secondId).getFriends().add(firstId);
         log.info("Пользователи {} и {} теперь друзья", firstId, secondId);
         return userStorage.getById(firstId);
     }

     public User removeFriend(int firstId, int secondId){
         if (!userStorage.getAllUsers().containsKey(firstId) || !userStorage.getAllUsers().containsKey(secondId)){
             throw new ObjectNotFoundException("Пользователей с таким Id не существует");
         }
         if (!userStorage.getById(firstId).getFriends().contains(secondId)){
             throw new InternalException("Пользователи уже являются друзьями");
         }
         userStorage.getById(firstId).getFriends().remove(secondId);
         userStorage.getById(secondId).getFriends().remove(firstId);
         log.info("Пользователи {} и {} больше не являются друзьями", firstId, secondId);
         return userStorage.getById(firstId);
     }

     public List<User> getManualFriends(int firstId, int secondId){
         if (!userStorage.getAllUsers().containsKey(firstId) || !userStorage.getAllUsers().containsKey(secondId)){
             throw new ObjectNotFoundException("Пользователей с таким Id не существует");
         }
         User firstFriend = userStorage.getById(firstId);
         User secondFriend = userStorage.getById(secondId);

         List<User> manualFriendsList = new ArrayList<>();
         log.info("Список общих друзей {} и {} сформирован", firstId, secondId);

         for (Integer friendId : firstFriend.getFriends()){
             for (Integer otherFriendId : secondFriend.getFriends()){
                 if(friendId == otherFriendId){
                     manualFriendsList.add(userStorage.getById(friendId));
                 }
             }
         }  return manualFriendsList;
     }

     public List<User> getFriendsList(int id){
         if (!userStorage.getAllUsers().containsKey(id)) {
             throw new ObjectNotFoundException("Пользователь не найден");
         }
         List<User> friendsList = new ArrayList<>();
         Set<Integer> friends = userStorage.getById(id).getFriends();

         log.info("Список друзей пользователя ID {} сформирован", id);
         for (Integer friendId : friends){
             friendsList.add(userStorage.getById(friendId));
         }
         return friendsList;
     }



}
