package com.crud.stream.repository;

import com.crud.stream.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PessoaRepository extends CrudRepository<Pessoa, String> {

}
