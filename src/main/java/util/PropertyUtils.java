package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtils {

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();

        try {
            properties.load(Objects.requireNonNull(PropertyUtils.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void findOne(Long userId, String query, Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
    }

}
