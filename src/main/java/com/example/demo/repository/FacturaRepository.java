package com.example.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Factura;

@Repository
public interface FacturaRepository extends MongoRepository<Factura, ObjectId> {
	
}
