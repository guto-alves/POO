package daocursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:sqlite:./src/daocursos/cursos.db";
	
	private static final DBUtil instance = new DBUtil();
	
	public static DBUtil getInstance() {
		return instance;
	}
	
	private Connection connection;
	
	public Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		return connection;
	}
}
