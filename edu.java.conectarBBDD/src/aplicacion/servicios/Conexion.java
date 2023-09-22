package aplicacion.servicios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	static String User;
	static String Pass;
	static String Url;

	public static Connection crearConexion() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		// Declaramos variable de tipo Properties
		Properties p = new Properties();

		try {
			// Cargamos el fichero properties
			p.load(new FileReader("src/aplicacion/servicios/configuracion.properties"));

			// Guardamos en las variables la url, user y pass del fichero properties
			Url = p.getProperty("URL");
			User = p.getProperty("USER");
			Pass = p.getProperty("PASS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Conectamos a la base de datos
		Connection conexion = null;
		java.sql.Statement stmt = null;
		try {
			conexion = DriverManager.getConnection(Url, User, Pass);
			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gbp_almacen.gbp_alm_cat_libros;");

			while (rs.next()) {
				int id_libro = rs.getInt("id_libro");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String isbn = rs.getString("isbn");
				int edicion = rs.getInt("edicion");
				System.out.printf("Id = %s , Titulo = %s, Autor = %s, Isbn = %s, Edicion = %s ", id_libro, titulo, autor, isbn, edicion);
			}

			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e);
		}

		return null;
	}
}
