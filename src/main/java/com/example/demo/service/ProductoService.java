package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository repositorio;

//	Agregar
//	Eliminar
//	ModificarPrecio
//	ModificarStock
//	Obtener Productos

	public String agregarProducto(Integer id, String nombre, Double precio, Integer stock, String imagen) {
		Optional<Producto> optionalProducto = repositorio.findById(id);
		if (optionalProducto.isEmpty()) {
			if (nombre.length() == 0) {
				return "Debe tener nombre";
			}
			if (precio <= 0) {
				return "El precio debe ser mayor a 0";
			}
			if (stock <= 0) {
				return "El stock debe ser mayor a 0";
			}

			Producto nuevo = new Producto(id, nombre, precio, stock, imagen);
			repositorio.save(nuevo);
			return "Producto agregado";
		} else {
			return "Ese id ya existe";
		}
	}

	public String eliminarProducto(Integer id) {
		Optional<Producto> productoOptional = repositorio.findById(id);
		if (productoOptional.isPresent()) {
			repositorio.deleteById(id);
			return "Producto eliminado";
		} else {
			return "Producto no encontrado";
		}
	}

	public String modificarPrecio(Integer id, Double precio) {
		Optional<Producto> productoOptional = repositorio.findById(id);
		if (productoOptional.isPresent()) {
			if (precio <= 0) {
				return "El precio debe ser mayor a 0";
			} else {
				Producto producto = productoOptional.get();
				producto.setPrecio(precio);
				repositorio.save(producto);
				return "Precio actualizado";
			}
		} else {
			return "Producto no encontrado";
		}
	}

	public String modificarStock(Integer id, Integer stock) {
		Optional<Producto> productoOptional = repositorio.findById(id);
		if (productoOptional.isPresent()) {
			if (stock < 0) {
				return "El stock debe ser positivo";
			} else {
				Producto producto = productoOptional.get();
				producto.setStock(stock);
				repositorio.save(producto);
				return "Stock actualizado";
			}

		} else {
			return "Producto no encontrado";
		}
	}

	public List<Producto> productos() {
		return repositorio.findAll();
	}

}
