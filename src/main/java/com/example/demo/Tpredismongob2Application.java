package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tpredismongob2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Tpredismongob2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Aca van los prints
		System.out.println("FUNCIONANDO");
		
	}

}
