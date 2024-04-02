package org.example.main.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

// TODO: 27.06.2023 Здесь тебе достаточно добавить одно поле userId;
//  Знаешь почему?
//  Потому что Я наследовал этот класс от User; P.S. помогает наследование!
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friend extends User {
    int id;

    public Friend(String name, String last_name, String email, int age, String address, int id) {
        super(name, last_name, email, age, address);
        this.id = id;
    }
}
