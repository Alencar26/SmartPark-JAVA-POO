package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConection {

	private String usuario = "SA";
	private String senha = "";
	private String PathBase = "C:\\Users\\andre\\eclipse-workspace\\SmartPark\\Dados\\dbPark";
	private String URL = "jdbc:hsqldb:file:" + PathBase + ";";
	
	
	public Connection conectar() {
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
			throw new Error("SQLException");
		}
	}
}
