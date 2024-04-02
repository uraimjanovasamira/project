package org.example.main.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)


// TODO: 27.06.2023 ID, NAME, LAST_NAME, EMAIL, AGE, ADDRESS, FRIEND_ID;
public class User {
    int id;
    String name;
    String last_name;
    String email;
    int age;
    String address;
    int friend_id;

    public User(String name, String last_name, String email, int age, String address) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public User(int id, String name, String lastName, String email, int age, String address, int friendId) {
    }
}
