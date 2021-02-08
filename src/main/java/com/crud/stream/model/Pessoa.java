package com.crud.stream.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update pessoa set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class Pessoa implements Serializable {

    @Id
    private String id;

    @NotNull
    private String nome;

    private int idade;

    private String nacionalidade;

    private String deleted;
}
