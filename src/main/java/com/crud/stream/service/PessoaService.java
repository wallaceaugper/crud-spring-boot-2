package com.crud.stream.service;

import com.crud.stream.model.Pessoa;
import com.crud.stream.repository.PessoaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    static Logger logger = LogManager.getLogger();
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll() {
        logger.info("Buscando todas pessoas na base");
        return (List<Pessoa>) this.pessoaRepository.findAll();
    }

    public ResponseEntity<Pessoa> findById(@PathVariable("id") String id) {
        logger.info("Buscando pessoa com id: {}", id);
        return this.pessoaRepository.findById(id)
                .map(pessoa -> {
                    logger.info("Pessoa encontrada: {}", pessoa.toString());
                    return ResponseEntity.ok().body(pessoa);
                })
                .orElseGet(() -> {
                    logger.error("Pessoa com id {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    public Pessoa create(@RequestBody Pessoa pessoa) {
        pessoa.setId(String.valueOf(UUID.randomUUID()));
        logger.info("[Create] Criando pessoa com o id: {}", pessoa.getId());
        return this.pessoaRepository.save(pessoa);
    }

    public List<Pessoa> createAll(@RequestBody List<Pessoa> pessoas) {
        pessoas.stream().forEach(pessoa -> {
            logger.info("[CreateAll] Criando pessoa com o id: {}", pessoa.getId());
            pessoa.setId(String.valueOf(UUID.randomUUID()));
        });
        return (List<Pessoa>) this.pessoaRepository.saveAll(pessoas);
    }

    public ResponseEntity<Pessoa> update(@PathVariable("id") String id, @RequestBody Pessoa pessoa) {
        logger.info("Atualizando pessoa com id: {}", id);
        return this.pessoaRepository.findById(id)
                .map(p -> {
                    p.setId(id);
                    p.setNome(pessoa.getNome());
                    p.setIdade(pessoa.getIdade());
                    p.setNacionalidade(pessoa.getNacionalidade());
                    Pessoa updated = this.pessoaRepository.save(p);
                    logger.info("Pessoa atualizada com sucesso");
                    return ResponseEntity.ok().body(updated);
                }).orElseGet(() -> {
                    logger.error("Pessoa com id {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    public ResponseEntity delete(@PathVariable("id") String id) {
        logger.info("Removendo pessoa com id: {}", id);
        return this.pessoaRepository.findById(id)
                .map(p -> {
                    this.pessoaRepository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    logger.error("Pessoa com id {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
