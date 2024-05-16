package com.trabalho.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.demo.model.Pessoa;
import com.trabalho.demo.service.PessoaService;
import com.trabalho.demo.service.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService service;

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa entity) {

        Pessoa pessoa = service.create(entity);

        return ResponseEntity.ok(pessoa);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findyById(@PathVariable String id) {
        return ResponseEntity.ok(service.findByid(id));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarPessoas(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return ResponseEntity.ok(service.buscarPorNome(nome));
        }
        return ResponseEntity.ok(service.buscarPessoas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updateById(@PathVariable String id, @RequestBody Pessoa pessoaUpdate) {
        try {
            return ResponseEntity.ok(service.updateById(id, pessoaUpdate));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
