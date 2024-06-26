package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente {

	@Id
	private String mail;
	// private String password;
	private String documento;
	private String nombre;
	private String apellido;
	private String direccion;
	private String categoria;
	ArrayList<Factura> facturas;
	private Carrito carrito;

	public Cliente() {

	}

	public Cliente(String documento, String nombre, String apellido, String mail, String password, String direccion) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		// this.password = password;
		this.direccion = direccion;
		this.facturas = new ArrayList<Factura>();

		this.carrito = new Carrito(new ArrayList<Detalle>());

		Random random = new Random();
//		Random entre 0 y 400
		Integer catego = random.nextInt(401);
		if (catego < 120) {
			this.categoria = "LOW";
		} else if (catego < 240) {
			this.categoria = "MEDIUM";
		} else {
			this.categoria = "TOP";

		}
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// public String getPassword() {
	// return password;
	// }

	// public void setPassword(String password) {
	// this.password = password;
	// }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "Cliente [documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail
				+ ", direccion=" + direccion + ", categoria=" + categoria + ", facturas=" + facturas + ", carrito="
				+ carrito + "]";
	}

}
