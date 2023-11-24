package es.studium.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente", schema = "hotel") // enlazamos la clase Cliente de Java con la tabla 'cliente'
public class Cliente {
	@Id   // el campo clave
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idCliente;
	// en el caso de que el nombre del atributo y el nombre del campo en la BD coinciden,
	// no es necesario utilizar la anotación
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "email")
	private String email;
	@Column(name = "dni")
	private String dni;
	@Column(name = "clave")
	private String clave;
	
	// declaramos los constructores
	public Cliente() {}
	
	public Cliente(int id) {
		this.idCliente = id;
	}
	
	public Cliente(String nombre, String apellidos, String email, String dni, String clave) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.dni = dni;
		this.clave = clave;
	}

	// declaramos los métodos getters y setters
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
