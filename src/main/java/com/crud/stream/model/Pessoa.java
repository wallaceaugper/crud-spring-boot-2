package com.crud.stream.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa implements Serializable {

    @Id
    private String id;

    @NotNull
    private String nome;

    private int idade;

    private String nacionalidade;
}
