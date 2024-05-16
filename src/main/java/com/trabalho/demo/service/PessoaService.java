package com.trabalho.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.trabalho.demo.model.Pessoa;
import com.trabalho.demo.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;

    public Pessoa create(Pessoa entity) {

        Pessoa pessoa = repository.save(entity);

        return pessoa;

    }

    public Pessoa findByid(String id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.orElseThrow();
    }

    public List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> pessoa = repository.findByNome(nome);
        return pessoa;
    }

    public List<Pessoa> buscarPessoas() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        Optional<Pessoa> pessoaDelete = repository.findById(id);
        Pessoa pessoa = pessoaDelete.orElseThrow();
        repository.delete(pessoa);
    }

    public Pessoa updateById(@PathVariable String id, @RequestBody Pessoa pessoaUpdate) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);
        Pessoa pessoa = pessoaOptional.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        pessoa.setNome(pessoaUpdate.getNome());
        pessoa.setIdade(pessoaUpdate.getIdade());
        return repository.save(pessoa);
    }

}
