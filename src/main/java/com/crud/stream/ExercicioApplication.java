package com.crud.stream;

import com.crud.stream.model.Pessoa;
import com.crud.stream.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ExercicioApplication {

	private final String PESSOA = "Pessoa ";

	public static void main(String[] args) {
		SpringApplication.run(ExercicioApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init (PessoaRepository pessoaRepository) {
//		return args -> {
//			for(int i = 0; i < 4; i++) {
//				Pessoa pessoa = new Pessoa(String.valueOf(UUID.randomUUID()), this.PESSOA + String.valueOf(i+1), (int) (Math.random() * (1 - 80) * -1), "brasileiro");
//				pessoaRepository.save(pessoa);
//			}
//		};
//	}
}
