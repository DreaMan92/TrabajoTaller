package Modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

public class Factura {
	
	private int id=0;
	private Date fecha_entrada;
	private double precio_total;
	private Date fecha_fin = null;
	private boolean pagado = false;
	private int id_vehiculo;
	private Vehiculo coche;
	private ArrayList<Reparacion> reparaciones = new ArrayList<Reparacion>();
	
	public Factura(Vehiculo coche) {
		this.id_vehiculo=coche.getId();
		this.coche=coche;
		fecha_entrada = new Date();
	};
	
	public Factura(int id, Date fecha_entrada, double precio_total, Date fecha_fin, boolean pagado, int id_vehiculo) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.precio_total = precio_total;
		this.fecha_fin = fecha_fin;
		this.pagado = pagado;
		this.id_vehiculo=id_vehiculo;
	}
	public Factura(int id, Date fecha_entrada, double precio_total, Date fecha_fin, boolean pagado, Vehiculo coche) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.precio_total = precio_total;
		this.fecha_fin = fecha_fin;
		this.pagado = pagado;
		this.coche = coche;
	}
	
	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	/*Se calcula en base a las horas de las reparaciones y las piezas utilizadas.
	 Se podria poner que tuviese dependencia de la clase del vehiculo. 
	 */
	public double getPrecio_total() {
		precio_total =0;
		reparaciones.forEach(rep ->{
			precio_total+=rep.getDuracion()*10;
			try {
				for (Entry<Pieza,Integer> linea : rep.getPiezas().entrySet()) {
					precio_total+=linea.getValue()*linea.getKey().getPrecio();
				}
				
			} catch (Exception e){
				//En caso de operacion sin piezas
			}
		});
		return precio_total;
	}
	
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public Vehiculo getCoche() {
		return coche;
	}
	public void setCoche(Vehiculo coche) {
		this.coche = coche;
	}
	public int getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	
	
}
