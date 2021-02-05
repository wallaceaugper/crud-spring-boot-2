package com.crud.stream.controller;

import com.crud.stream.model.Pessoa;
import com.crud.stream.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/all")
    public List<Pessoa> findAll() {
        return this.pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return this.pessoaService.findById(id);
    }

    @PostMapping("/create")
    public Pessoa create(@RequestBody Pessoa pessoa) {
        return this.pessoaService.create(pessoa);
    }

    @PostMapping("/create-all")
    public List<Pessoa> createAll(@RequestBody List<Pessoa> pessoas) {
        return this.pessoaService.createAll(pessoas);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Pessoa pessoa) {
        return this.pessoaService.update(id, pessoa);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        return this.pessoaService.delete(id);
    }
}
