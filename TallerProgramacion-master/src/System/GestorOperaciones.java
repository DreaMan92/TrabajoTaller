package System;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import DAO.Consultas;
import DAO.Inserts;
import DAO.Updates;
import Modelos.Factura;
import Modelos.Pieza;
import Modelos.Reparacion;
import Modelos.Vehiculo;
public class GestorOperaciones {
	
	private String username;
	private String password;
	private ArrayList<Vehiculo> coches;
	private ArrayList<Factura> facturas;
	private ArrayList<Pieza> piezas;
	private ArrayList<Reparacion> reparaciones; 
	private Consultas consultasDB;
	private Inserts insertsDB;
	private Updates updatesDB;
	
	public GestorOperaciones(String username, String password) throws SQLException {
		this.username=username;
		this.password=password;
		consultasDB = new Consultas(username,password);
		insertsDB = new Inserts(username,password);
		updatesDB = new Updates(username,password);
		/*Obtenemos los datos de la base de datos*/
		coches = consultasDB.verVehiculos();
		piezas = consultasDB.verPiezas();
		facturas = consultasDB.verFacturas();
		reparaciones = consultasDB.verReparaciones();
		/*Asignamos el vehiculo a cada factura usando las claves primarias*/
		facturas.forEach(factura ->{
			factura.setCoche(buscarPorID(factura.getId_vehiculo()));
		});
		/*Asignamos a que factura corresponde cada reparacion.*/
		reparaciones.forEach(rep ->{
			Factura factura = buscarFacturaPorId(rep.getId_factura());
			factura.getReparaciones().add(rep);
		});
	}
	
	/*Devuelve la factura con el ID especificado*/
	private Factura buscarFacturaPorId(int id) {
		for(Factura factura : facturas) {
			if (factura.getId()==id) return factura;
		}
		return null;
	}
	
	/*Devuelve el vehiculo con el ID buscado*/
	private Vehiculo buscarPorID(int id) {
		for(Vehiculo coche : coches) {
			if (coche.getId()==id) return coche;
		}
		return null;
	}
	
	/*Devuelve una factura abierta del coche. Si no la encuentra le crea una nueva y la escribe en la base de datos*/
	public Factura buscarFacturaActualCoche(Vehiculo coche) throws SQLException {
		for(Factura factura : facturas) {
			if (factura.getCoche()==coche && factura.getFecha_fin()==null) {
				return factura;
			}
		}
		
		/*En caso de que sea nueva factura, la escribimos en la base de datos y obtenemos su id*/
		Factura factura = new Factura(coche);
		insertsDB.insertarFactura(factura);
		factura.setId(consultasDB.last_factura_id());
		facturas.add(factura);
		return factura;
	}

	/*Actualiza el stock de la pieza restando la cantidad introducida*/
	private void restarPiezas(Pieza pieza, int cantidad) throws SQLException {
		updatesDB.restarStockPieza(pieza, cantidad);
		pieza.setStock(pieza.getStock()-cantidad);
	}
	
	public ArrayList<Pieza> piezasEnStock(){
		ArrayList<Pieza> piezerio = new ArrayList<Pieza>();
		piezas.forEach(piez -> {
			if (piez.getStock()>0) piezerio.add(piez);
		});
		return piezerio;
	}
	/*
	 Guarda la reparacion en la base de datos, junto a todas la lineas correspondientes de la tabla USA (piezas-cantidad-reparacion)
	 Tambien actualiza el stock de piezas de la tabla piezas.
	  */
	public void anadirReparacion(Reparacion rep) throws SQLException {
		insertsDB.insertarReparacion(rep);
		for (Entry<Pieza,Integer> linea : rep.getPiezas().entrySet()) {
			restarPiezas(linea.getKey(),linea.getValue());
		}
	}
	
	/*Cuando un vehiculo ha sido totalmente reparado (1 o mas reparaciones)
	 Sobre escribe la linea en la tabla factura, actualizando el precio total, la fecha fin y si esta pagado o no. 
	 */
	public void terminarReparacionCoche(Factura factura) throws SQLException {
		factura.setFecha_fin(new Date());
		/*Hacer update en lugar de insert*/
		updatesDB.actualizarFactura(factura);
	}
	
	/*A�ade piezas a la reparacion vigente*/
	public void anadirPiezasReparacion(Pieza pieza,int cantidad,Reparacion rep) {
		rep.getPiezas().put(pieza, cantidad);
	}
	
	public ArrayList<Vehiculo> vehiculosEnReparacion(){
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		facturas.forEach(factura ->{
			if (factura.getFecha_fin()==null) vehiculos.add(factura.getCoche());
		});
		return vehiculos;
	}

	public ArrayList<Vehiculo> getCoches() {
		return coches;
	}

	public void setCoches(ArrayList<Vehiculo> coches) {
		this.coches = coches;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}

	public void setPiezas(ArrayList<Pieza> piezas) {
		this.piezas = piezas;
	}

	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}	
	
	
}
