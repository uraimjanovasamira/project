package org.example;

import org.example.main.model.Friend;
import org.example.main.model.User;
import org.example.main.service.impl.FriendService;
import org.example.main.service.impl.UserService;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    /**
     * Я В ТЕБЕ ВЕРЮ, У ТЕБЯ ВСЕ ПОЛУЧИТСЯ!!!
     */
    public static void main(String[] args) {
        UserService userService = new UserService();

//        userService.save(
//                new User("Sami","Uraimjanova","sami@gmail.com",16,"Moscow"));
//        userService.update(1,
//                new User("Rabi","Buzurmanalieva","rabi@gmail.com",17,"Moscow"));
//
//        userService.deleteById(1);
        List<User> users = userService.getAll();
        users.forEach(System.out::println);

    }
}
