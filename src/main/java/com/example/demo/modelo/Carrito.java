package com.example.demo.modelo;

import java.util.ArrayList;

public class Carrito {

	private ArrayList<Detalle> detalles;

	public Carrito() {

	}

	public Carrito(ArrayList<Detalle> detalles) {
		super();
		this.detalles = detalles;
	}

	public ArrayList<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Double totalCarrito() {
		Double total = 0.0;
		for (Detalle detalle : detalles) {
			total = total + detalle.totalDetalle();
		}
		return total;
	}

//  Nuevo
	public Boolean productoEnCarrito(Producto p) {
		for (Detalle detalle : this.detalles) {
			if (detalle.getProducto().getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	public void agregarDetalleCarrito(Detalle detalle) {
		this.detalles.add(detalle);
		System.out.println("Detalle agregado");
	}

	public void eliminarDetalleCarrito(Producto p) {
		for (Detalle detalle : detalles) {
			Producto productoDetalle = detalle.getProducto();
			if (productoDetalle.getId() == p.getId()) {
				System.out.println("Producto elminado");
				detalles.remove(detalle);
				return;
			}
		}
	}

	public void modificarCantidadProducto(Producto p, Integer cantidad) {
		for (Detalle detalle : detalles) {
			Producto producDetalle = detalle.getProducto();
			Integer stock = producDetalle.getStock();
			if (producDetalle.getId() == p.getId()) {
				if (detalle.getCantidad() + cantidad > stock) {
					detalle.setCantidad(stock);
				} else {
					detalle.setCantidad(detalle.getCantidad() + cantidad);
				}

				System.out.println("Cantidad modificada");
			}
		}
	}

	public void actualizarCantidadCarrito(Producto p, Integer cantidad) {
		for (Detalle detalle : detalles) {
			Producto producDetalle = detalle.getProducto();
			if (producDetalle.getId() == p.getId()) {
				detalle.setCantidad(cantidad);
				System.out.println("Cantidad modificada");
			}
		}
	}

	@Override
	public String toString() {
		return "Carrito [detalles=" + detalles + "]";
	}

}
