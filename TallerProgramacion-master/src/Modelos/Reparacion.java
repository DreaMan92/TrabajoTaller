package Modelos;

import java.util.Date;
import java.util.HashMap;

public class Reparacion {
	
	private int id;
	private Date fecha_hora;
	private float duracion;
	private String comentarios;
	private int id_factura;
	private	HashMap<Pieza,Integer> piezas;
	
	public Reparacion() {};
	
	public Reparacion( Date fecha_hora, float duracion, String comentarios, int id_factura, HashMap<Pieza,Integer> piezas) {
		super();
		this.fecha_hora = fecha_hora;
		this.duracion = duracion;
		this.comentarios = comentarios;
		this.id_factura = id_factura;
		this.piezas=piezas;
	}

	public Reparacion(int id, Date fecha_hora, float duracion, String comentarios, int id_factura) {
		super();
		this.id = id;
		this.fecha_hora = fecha_hora;
		this.duracion = duracion;
		this.comentarios = comentarios;
		this.id_factura = id_factura;
	}
	
	public Date getFecha_hora() {
		return fecha_hora;
	}


	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}


	public int getId_factura() {
		return id_factura;
	}


	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHora() {
		return fecha_hora;
	}
	public void setHora(Date hora) {
		this.fecha_hora = hora;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public HashMap<Pieza, Integer> getPiezas() {
		return piezas;
	}
	public void setPiezas(HashMap<Pieza, Integer> piezas) {
		this.piezas = piezas;
	}
	
	
	
}
