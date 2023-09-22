package aplicacion.controladores;

import java.sql.SQLException;

import aplicacion.servicios.Conexion;

public class Menu {

	public static void main(String[] args) {
		
		try {
			Conexion.crearConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
