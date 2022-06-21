package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelos.Factura;
import Modelos.Pieza;

public class Updates {
	
	private String username;
	private String password;
	
	public Updates(String username,String password) {
		this.username=username;
		this.password=password;
	}
	
	/*Actualiza la factura que ha sido finalizada, estableciendo el precio final y la fecha final*/
	public void actualizarFactura(Factura factura) throws SQLException {
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "update factura set  precio_total=? , fecha_fin = ?,pagado=? where id_factura=?";
			PreparedStatement stmt = conn.prepareStatement(ssql);
			stmt.setDate(2, new java.sql.Date (factura.getFecha_fin().getTime()));
			stmt.setDouble(1, factura.getPrecio_total());
			stmt.setBoolean(3, factura.isPagado());
			stmt.setInt(4, factura.getId());
			stmt.executeUpdate();
			conn.close();
		} catch(Exception e) {
			conn.close();
		}
	}
	
	/*Cambia el stock de la pieza especificada*/
	public void restarStockPieza(Pieza pieza,int cantidad) throws SQLException {
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "update piezas set stock=? where id_pieza=?";
			PreparedStatement stmt = conn.prepareStatement(ssql);
			stmt.setInt(1,pieza.getStock()-cantidad);
			stmt.setInt(2, pieza.getId());
			stmt.executeUpdate();
			conn.close();
		} catch(Exception e) {
			conn.close();
		}
	}
}
