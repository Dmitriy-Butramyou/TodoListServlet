package jdbc.connector;

import util.PropertyUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySqlConnector {

    private static MySqlConnector instance;
    private static Connection connection;

    /**
     * метод для соединения с БД по параметрам,
     * читаемые из файла connection.properties
     */
    private MySqlConnector(){
        Properties properties = PropertyUtils.getProperties("connection.properties");
        Connection conn = null;
        try {
            Class.forName(properties.getProperty("driver"));
            try {
                conn = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = conn;
    }

    /**
     * статический метод, возвращает единственный экземпляр класса,
     * проверяя перед этим не создан ли он
     */
    public static MySqlConnector getInstance() {
        if(instance == null) {
            instance = new MySqlConnector();
        }
        return instance;
    }

    /**
     * получаем соединение
     */
    public Connection getConnection() {
        return connection;
    }
}
