package edu.mefpd.academy.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//JAVA BEAN / pojo /DTO
@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto inc en mysql
	private Long id;
	
	@Size(min = 3, max = 20)
	private String nombre;
	
	@Min(10)
	@Max(100)
	private int edad;
	
	@Email
	private String email;
	
	@NotEmpty//esto debe tener longitudo mayor o igual a 1
	private String apellido;
	
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;
	
	@PrePersist
	private void generarFechaCreacion ()
	{
		this.creadoEn = LocalDateTime.now();//obtengo la fecha de creación
	}
	
	public Alumno() {
		super();
	}
	
	public Alumno(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	
	
	
	public Alumno(Long id, String nombre, int edad, String email, String apellido, LocalDateTime creadoEn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		this.apellido = apellido;
		this.creadoEn = creadoEn;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", email=" + email + ", apellido="
				+ apellido + ", creadoEn=" + creadoEn + "]";
	}
	
	

}
