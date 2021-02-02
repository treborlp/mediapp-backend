package com.ral.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;
	
	@Size(min = 3, message = "{nombres.size}")
	@Column(name="nombres", nullable = false, length = 70)
	private String nombres;
	
	@Size(min = 3, message = "{apellidos.size}")
	@Column(name="apellidos", nullable = false, length = 70)
	private String apellidos;
	
	@Size(min = 3, message = "La dirección debe tener como mínimo 3 digitos")
	@Column(name="direcion", nullable = true, length = 150)
	private String direcion;
	
	@Size(min = 8, message = "El dni debe tener como minimo 8 digitos")
	@Column(name="dni", nullable = false, length = 8, unique=true)
	private String dni;
	
	@Size(min = 9, message = "El telefono debe tener como minimo 9 digitos")
	@Column(name="telefono", nullable = true, length = 9)
	private String telefono;
	
	@Email
	@Column(name="email", nullable = true, length = 55)
	private String email;
	
	
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDirecion() {
		return direcion;
	}
	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
