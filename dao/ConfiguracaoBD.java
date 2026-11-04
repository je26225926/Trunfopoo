package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracaoBD {

    private static final String URL =
            "jdbc:mysql://acela.proxy.rlwy.net:48948/railway";

    private static final String USUARIO = "root";

    private static final String SENHA =
            "QrhSZWKfcLxgwDkXMwPYKxLWnbrtlMMP";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
