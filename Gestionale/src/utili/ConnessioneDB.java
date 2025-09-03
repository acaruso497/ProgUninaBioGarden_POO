package utili;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnessioneDB {
	public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Gestionale";
        String user = "postgres";
        String password = "Admin"; // cambia se serve

        return DriverManager.getConnection(url, user, password);
	}
}
