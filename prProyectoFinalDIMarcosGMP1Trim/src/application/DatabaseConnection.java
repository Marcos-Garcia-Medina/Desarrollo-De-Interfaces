package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que nos ayudara a conectarnos con la base de datos.
 * @author Marcos Garcia Medina.
 */
public class DatabaseConnection {

	/**
	 *	connection: Objeto que nos permitira conectar a nuestra base de datos.
	 */
	Connection connection;
	
	/**
	 * Funcion necesaria para conectarse con la base de datos.
	 * @return Objeto connection para conectarnso a una base de datos especifica.
	 */
	public Connection getConnection () {
		String dbName = "bd_backroomsdi";
		String userName = "root";
		String password = "admin";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + dbName,
					userName,
					password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
}
