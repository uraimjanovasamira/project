package org.example.main.service.impl;

import org.example.main.config.Config;
import org.example.main.model.Friend;
import org.example.main.model.Post;
import org.example.main.model.User;
import org.example.main.service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// TODO: 27.06.2023
//  Здесь ты должен реализовать весь CRUD
//  (create -save,
//  read - findById || getAll,
//  update - update,
//  delete - delete) и дополнительные методы!!!
public class UserService implements Service<User> {
    @Override
    public void createTable() {
        String query = """
                    CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR,
                        last_name VARCHAR,
                        email VARCHAR,
                        age INTEGER,
                        address VARCHAR,
                        friend_id INTEGER
                    );
                """;
        try (
                Connection connection = Config.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void save(User user) {
        String query = """
                    INSERT INTO users (name, last_name, email, age, address, friend_id)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getFriend_id());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
                user.setAddress(resultSet.getString("address"));
                user.setFriend_id(resultSet.getInt("friend_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return user;
    }

    @Override
    public void update(int id, User user) {
        String query = """
                    UPDATE users
                     SET name = ?, last_name = ?, email = ?, age = ?, address = ?, friend_id = ?
                    WHERE id = ?
                """;

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getFriend_id());
            preparedStatement.setInt(7, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
                user.setAddress(resultSet.getString("address"));
                user.setFriend_id(resultSet.getInt("friend_id"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return users;
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    public User followYou(int friendId) {

        return null;
    }

    public List<Friend> getAllFriends() {

        return null;
    }

    public Post sendLike(int postId) {
        return null;
    }

    public List<Post> getNewPosts() {
        return null;
    }

    public Post getPopularPost() {
        return null;
    }
}