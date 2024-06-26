package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, Integer> {

}
