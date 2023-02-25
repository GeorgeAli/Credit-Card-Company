package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_db {

    // with_database == 1 returns connect object with database cccdb, else returns connection with localhost
    public static Connection get_connection(boolean with_database) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String databaseName = "/cccdb?characterEncoding=UTF-8";
        String username = "root";
        String password = "";
        if (with_database) {
            return DriverManager.getConnection(url + databaseName, username, password);
        } else {
            return DriverManager.getConnection(url, username, password);
        }
    }

}
