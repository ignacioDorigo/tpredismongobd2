package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
