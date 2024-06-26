package com.example.demo.modelo;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facturas")
public class Factura {

	private Integer numeroFactura;
	private String nombreCliente;
	private String apellidoCliente;
	private String dniCliente;
	private String medioPago;
	private Carrito carritoCliente;
	private String condicionFiscal;
	private Double total;
	private LocalDateTime fecha;

	public Factura(String nombreCliente, String apellidoCliente, String dniCliente, String medioPago,
			Carrito carritoCliente, String condicionFiscal) {

		Random random = new Random();
		Integer numeroFact = random.nextInt(10000);
		this.numeroFactura = numeroFact;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.dniCliente = dniCliente;
		this.medioPago = medioPago;
		this.carritoCliente = carritoCliente;
		this.condicionFiscal = condicionFiscal;
		this.total = carritoCliente.totalCarrito();
		this.fecha = LocalDateTime.now();
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public Carrito getCarritoCliente() {
		return carritoCliente;
	}

	public void setCarritoCliente(Carrito carritoCliente) {
		this.carritoCliente = carritoCliente;
	}

	public String getCondicionFiscal() {
		return condicionFiscal;
	}

	public void setCondicionFiscal(String condicionFiscal) {
		this.condicionFiscal = condicionFiscal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Factura [numeroFactura=" + numeroFactura + ", nombreCliente=" + nombreCliente + ", apellidoCliente="
				+ apellidoCliente + ", dniCliente=" + dniCliente + ", medioPago=" + medioPago + ", carritoCliente="
				+ carritoCliente + ", condicionFiscal=" + condicionFiscal + ", total=" + total + ", fecha=" + fecha
				+ "]";
	}

}
