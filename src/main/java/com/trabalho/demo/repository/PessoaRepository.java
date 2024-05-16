package com.trabalho.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.demo.model.Pessoa;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    Optional<Pessoa> findById(String id);

    List<Pessoa> findByNome(String nome);

}
