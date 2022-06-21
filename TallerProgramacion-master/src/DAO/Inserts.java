package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map.Entry;

import Modelos.Factura;
import Modelos.Pieza;
import Modelos.Reparacion;

public class Inserts {
	
	private String username;
	private String password;
	
	public Inserts(String username,String password) {
		this.username=username;
		this.password=password;
	}
	
	/*Inserta una nueva reparacion en la base de datos, junto a las lineas correspondientes en la tabla usa*/
	public void insertarReparacion(Reparacion rep) throws SQLException {
		
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "insert into reparacion(tiempo_hora,horas_duracion,comentarios, id_factura) values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(ssql);
			stmt.setDate(1, new java.sql.Date(rep.getHora().getTime()));
			stmt.setFloat(2, rep.getDuracion());
			stmt.setString(3, rep.getComentarios());
			stmt.setInt(4,rep.getId_factura());
			stmt.executeUpdate();
			int id_rep = new Consultas(username,password).last_rep_id();
			for(Entry<Pieza, Integer> entrada : rep.getPiezas().entrySet()) {
				String ssql2 = "insert into usa values(?,"+id_rep+",?)";
				PreparedStatement stmt2 = conn.prepareStatement(ssql2);
				stmt2.setInt(1, entrada.getKey().getId());
				stmt2.setInt(2, entrada.getValue());
				stmt2.executeUpdate();
			}
			conn.close();
		} catch(Exception e) {
			conn.close();
		}
	}
	
	/*Inserta una nueva factura en la bbdd*/
	public void insertarFactura(Factura factura) throws SQLException{
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "insert into factura(fecha_entrada,precio_total, pagado,id_vehiculo) values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(ssql);			
			stmt.setDate(1,new java.sql.Date(factura.getFecha_entrada().getTime()));
			stmt.setDouble(2, factura.getPrecio_total());
			stmt.setInt(3, factura.isPagado() ? 1 : 0);
			stmt.setInt(4,factura.getId_vehiculo());
			stmt.executeUpdate();
			conn.close();	
		} catch(Exception e) {
			e.printStackTrace();
			conn.close();
		}
	}
}
