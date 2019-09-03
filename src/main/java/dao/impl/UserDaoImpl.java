package dao.impl;

import dao.UserDao;
import jdbc.connector.MySqlConnector;
import jdbc.query.MySqlQuery;
import model.User;
import util.CloseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection = MySqlConnector.getInstance().getConnection();

    @Override
    public User getOne(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("userGetOne");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new  User
                        .Builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .password(resultSet.getString(3))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        String query = MySqlQuery.getInstance().getQuery("userGetAll");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(
                        new User
                                .Builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .password(resultSet.getString(3))
                                .build()
                );
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User save(User user) {
        PreparedStatement preparedStatement = null;
        String query = user.getId() != null ?
                MySqlQuery.getInstance().getQuery("userUpdate") :
                MySqlQuery.getInstance().getQuery("userInsert");
        try {
            preparedStatement = user.getId() != null ?
                    connection.prepareStatement(query) :
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            if (user.getId() != null) {
                preparedStatement.setLong(3, user.getId());
                return preparedStatement.executeUpdate() > 0 ? user : null;
            } else {
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                    resultSet.close();
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, null);
        }
        return null;
    }
}
