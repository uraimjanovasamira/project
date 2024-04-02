package org.example.main.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

// TODO: 27.06.2023 ID,
//  NAME,
//  DESCRIPTION,
//  CREATED(дата создание поста),
//  likeCounter(количество лайков, поставленных на этот пост),
//  userId(с каим пользователем связано),
//  тип поста(видео или фото).
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Post {
    int id;
    String name;
    String description;
    int created;
    int likeCounter;
    int userId;
    String typeOfPost;



}
