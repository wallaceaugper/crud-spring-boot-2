package com.crud.stream.service;

import com.crud.stream.model.Pessoa;
import com.crud.stream.repository.PessoaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll() {
        return (List<Pessoa>) this.pessoaRepository.findAll();
    }

    public ResponseEntity<Pessoa> findById(@PathVariable("id") String id) {
        return this.pessoaRepository.findById(id)
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    public Pessoa create(@RequestBody Pessoa pessoa) {
        pessoa.setId(String.valueOf(UUID.randomUUID()));
        return this.pessoaRepository.save(pessoa);
    }

    public List<Pessoa> createAll(@RequestBody List<Pessoa> pessoas) {
        pessoas.stream().forEach(pessoa -> pessoa.setId(String.valueOf(UUID.randomUUID())));
        return (List<Pessoa>) this.pessoaRepository.saveAll(pessoas);
    }

    public ResponseEntity<Pessoa> update(@PathVariable("id") String id, @RequestBody Pessoa pessoa) {
        return this.pessoaRepository.findById(id)
                .map(p -> {
                    p.setId(id);
                    p.setNome(pessoa.getNome());
                    p.setIdade(pessoa.getIdade());
                    p.setNacionalidade(pessoa.getNacionalidade());
                    Pessoa updated = this.pessoaRepository.save(p);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity delete(@PathVariable("id") String id) {
        return this.pessoaRepository.findById(id)
                .map(p -> {
                    this.pessoaRepository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
