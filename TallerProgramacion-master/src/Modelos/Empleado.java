package Modelos;

public class Empleado {
	
	private int id;
	private String nombre;
	private String apellido;
	private String telefondo;
	private String dni;
	private int id_jefe;
	
	public Empleado(){};
	
	public Empleado(int id, String nombre, String apellido, String telefondo, String dni, int id_jefe) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefondo = telefondo;
		this.dni = dni;
		this.id_jefe = id_jefe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefondo() {
		return telefondo;
	}
	public void setTelefondo(String telefondo) {
		this.telefondo = telefondo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getId_jefe() {
		return id_jefe;
	}
	public void setId_jefe(int id_jefe) {
		this.id_jefe = id_jefe;
	}
	
}
