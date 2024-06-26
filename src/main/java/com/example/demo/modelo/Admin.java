package com.example.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administradores")
public class Admin {

	@Id
	private String mail;
	private String nombre;
	private String apellido;
	private String documento;

	public Admin() {

	}

	public Admin(String mail, String nombre, String apellido, String documento) {
		super();
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "Admin [mail=" + mail + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
				+ "]";
	}

}
