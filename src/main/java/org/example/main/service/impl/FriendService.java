package org.example.main.service.impl;

import org.example.main.config.Config;
import org.example.main.model.Friend;
import org.example.main.model.User;
import org.example.main.service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendService implements Service<Friend> {

    @Override
    public void createTable() {
        String query = """
                    CREATE TABLE friends (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR,
                        last_name VARCHAR,
                        email VARCHAR,
                        age INTEGER,
                        address VARCHAR );
                """;

        try (
                Connection connection = Config.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void save(Friend friend) {
        String query = """
                    INSERT INTO friends (name, last_name, email, age, address)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, friend.getName());
            preparedStatement.setString(2, friend.getLast_name());
            preparedStatement.setString(3, friend.getEmail());
            preparedStatement.setInt(4, friend.getAge());
            preparedStatement.setString(5, friend.getAddress());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
            Friend friend = null;
            String query = "SELECT * FROM friends WHERE id = ?";

            try (Connection connection = Config.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    friend = new Friend();
                    friend.setId(resultSet.getInt("id"));
                    friend.setName(resultSet.getString("name"));
                    friend.setLast_name(resultSet.getString("last_name"));
                    friend.setEmail(resultSet.getString("email"));
                    friend.setAge(resultSet.getInt("age"));
                    friend.setAddress(resultSet.getString("address"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
            return friend;
        }


        @Override
    public void update(int id, Friend friend) {
        String query = """
                    UPDATE friends
                    SET name = ?, last_name = ?, email = ?, age = ?, address = ?
                    WHERE id = ?
                """;

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, friend.getName());
            preparedStatement.setString(2, friend.getLast_name());
            preparedStatement.setString(3, friend.getEmail());
            preparedStatement.setInt(4, friend.getAge());
            preparedStatement.setString(5, friend.getAddress());
            preparedStatement.setInt(6, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Friend> getAll() {
        List<Friend> friends = new ArrayList<>();
        String query = "SELECT* FROM friends";
        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Friend friend = new Friend();
                friend.setId(resultSet.getInt("id"));
                friend.setName(resultSet.getString("name"));
                friend.setLast_name(resultSet.getString("last_name"));
                friend.setEmail(resultSet.getString("email"));
                friend.setAge(resultSet.getInt("age"));
                friend.setAddress(resultSet.getString("address"));


                friends.add(friend);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return friends;

    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM friends WHERE id = ?";

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
