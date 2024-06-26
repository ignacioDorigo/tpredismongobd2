package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Factura;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.FacturaRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repositorio;

	@Autowired
	EmailSenderService emailSenderService;

	@Autowired
	FacturaRepository facturaRepository;

	public void guardarEnRedis(String mailAdmin, String password) {
		// Crear una fábrica de conexiones Lettuce
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet(); // Inicializa la fábrica de conexiones

		// Crear una plantilla de Redis (clave , valor)
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory); // Configura la plantilla con la fábrica de conexiones
		template.setDefaultSerializer(StringRedisSerializer.UTF_8); // Establece el serializador para las claves y //
																	// valores
		template.afterPropertiesSet(); // Inicializa la plantilla de Redis

////      Mostrar objeto guardado
//		System.out.println(template.opsForValue().get("pedro@gmail.com"));
		// Cerrar la fábrica de conexiones

		String clave = "admin:" + mailAdmin;

		try {
			Boolean actualizado = template.opsForValue().setIfPresent(clave, password);
			if (actualizado != null && actualizado) {
				System.out.println("Usuario existente actualizado en Redis");
			} else {
				Boolean creado = template.opsForValue().setIfAbsent(clave, password);
				if (creado != null && creado) {
					System.out.println("Nuevo usuario creado en Redis");
				} else {
					System.out.println("No se pudo guardar el usuario en Redis");
				}
			}
		} finally {
			connectionFactory.destroy();
		}
	}

	public boolean loginRedis(String mail, String password) {
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet(); // Inicializa la fábrica de conexiones

		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(StringRedisSerializer.UTF_8);
		template.afterPropertiesSet();

//		Si encuentra el mail, devuelve el password
		String clave = "admin:" + mail;
		String resultado = template.opsForValue().get(clave);
		if (resultado != null) {
			System.out.println("Mail Redis encontrado");
			if (resultado.equals(password)) {
				System.out.println("Login Exitoso en redis");
				return true;
			} else {
				System.out.println("La contrasenia es invalida");
				return false;
			}
		} else {
			System.out.println("Mail Redis No encontrado");
			return false;
		}

	}

	public String registerAdmin(String mail, String password, String nombre, String apellido, String documento) {
		Optional<Admin> adminOptional = repositorio.findById(mail);
		if (adminOptional.isEmpty()) {
			Admin nuevo = new Admin(mail, nombre, apellido, documento);
			repositorio.save(nuevo);
			emailSenderService.sendEmail("ferorrego67@gmail.com", "Registro en APP",
					"Te has registrado exitosamente en la app");
			guardarEnRedis(mail, password);
			return "Register Exitoso";
		} else {
			return "Ya existe ese mail";
		}
	}

	public String eliminarAdmin(String mail) {
		Optional<Admin> adminOptional = repositorio.findById(mail);
		if (adminOptional.isPresent()) {
			repositorio.deleteById(mail);
			return "Admin Eliminado";
		} else {
			return "Admin No Existe";

		}
	}

	public String loginAdmin(String mail, String password) {
		if (loginRedis(mail, password)) {
			return "Login Exitoso";

		} else {
			return "Login No exitoso";
		}
	}

	public String olvideContrasena(String mail) {
		String contrasena = extraerContrasenaRedis(mail);
		if (contrasena != null) {

			emailSenderService.sendEmail("ferorrego67@gmail.com", "Recupero contrasenia en APP",
					"Tu contrasenia es : " + contrasena);
			return "Envio de contrasenia al mail";
		} else {
			return "Admin encontrado";
		}
	}

	public Admin perfilAdmin(String mail) {
		Optional<Admin> adminOptional = repositorio.findById(mail);
		if (adminOptional.isPresent()) {
			Admin admin = adminOptional.get();
			return admin;
		} else {
			return null;
		}
	}

	public String cambiarContraseniaAdmin(String mail, String actual, String nueva1, String nueva2) {
		String contrasenia = extraerContrasenaRedis(mail);
		if (contrasenia != null) {
			System.out.println("contrasenia: " + contrasenia);
			if (contrasenia.equals(actual)) {
				if (nueva1.equals(nueva2)) {
					emailSenderService.sendEmail("ferorrego67@gmail.com", "Cambio contrasenia en APP",
							"Has cambiado tu contrasenia, tu nueva contrasenia es: " + nueva1);
					guardarEnRedis(mail, nueva1);
					return "Cambio contrasenia exitoso";

				} else {
					return "Las contrasenias nuevas no coinciden";
				}
			} else {
				return "No coincide la contrasenia actual";
			}
		} else {
			return "Admin no encontrado";
		}
	}

	public List<Factura> facturas() {
		return facturaRepository.findAll();
	}

	public String extraerContrasenaRedis(String mail) {

		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet(); // Inicializa la fábrica de conexiones

		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(StringRedisSerializer.UTF_8);
		template.afterPropertiesSet();

//		Si encuentra el mail, devuelve el password
		String clave = "admin:" + mail;
		String resultado = template.opsForValue().get(clave);

		return resultado;
	}

}
