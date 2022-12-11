//package ru.yandex.practicum.filmorate.storage;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.stereotype.Component;
//import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
//import ru.yandex.practicum.filmorate.model.User;
//import ru.yandex.practicum.filmorate.storage.user.UserStorage;
//
//import javax.validation.ValidationException;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class InMemoryUserStorage implements UserStorage {
//
//    private final Map<Integer, User> userMap = new HashMap<>();
//
//    private int userId = 1;
//
//    @Override
//    public Map<Integer, User> getAllUsers(){
//        return userMap;
//    }
//
//    @Override
//    public Collection<User> findAllUsers(){
//        return userMap.values();
//    }
//
//    @Override
//    public User create(User user){
//        user.setId(userId++);
//        if (postUserValidation(user)){
//            userMap.put(user.getId(), user);
//            log.info("Пользователь с логином {} создан", user.getLogin());
//        }
//        return user;
//    }
//
//    @Override
//    public User update(User user){
//        if (putUserValidation(user)){
//            userMap.put(user.getId(), user);
//            log.info("Информация о пользователе с логином {} обновлена", user.getLogin());
//        }
//        return user;
//    }
//
//    @Override
//    public User getById(int id){
//        return userMap.get(id);
//    }
//
//    @Override
//    public User deleteById(int id){
//        User user = userMap.get(id);
//        userMap.remove(id);
//        return user;
//    }
//
//
//
//
//
//    public  boolean postUserValidation(User user) {
//        boolean isValid;
//        if (userMap.containsKey(user.getId())) {
//            isValid = false;
//            throw new ValidationException("Пользователь с таким ID уже существует");
//        } else {
//            isValid = true;
//            if (user.getName() == null || user.getName().isBlank()) {
//                user.setName(user.getLogin());
//            }
//            return isValid;
//        }
//    }
//    public  boolean putUserValidation(User user){
//        boolean isValid;
//        if (!userMap.containsKey(user.getId())){
//            isValid = false;
//            throw new ObjectNotFoundException("Такого пользователя не существует, необходимо сначала зарегистрировать" +
//                    "пользователя");
//        } else {
//            isValid = true;
//        } return isValid;
//    }
//
//}
