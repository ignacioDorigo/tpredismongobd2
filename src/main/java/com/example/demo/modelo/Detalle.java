package com.example.demo.modelo;

public class Detalle {
	private Producto producto;
	private Integer cantidad;

	public Detalle() {

	}

	public Detalle(Producto producto, Integer cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double totalDetalle() {
		Double total = producto.getPrecio() * cantidad;
		return total;
	}

	@Override
	public String toString() {
		return "Detalle [producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}