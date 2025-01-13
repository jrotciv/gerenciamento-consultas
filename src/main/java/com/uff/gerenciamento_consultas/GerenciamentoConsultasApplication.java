package com.uff.gerenciamento_consultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GerenciamentoConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoConsultasApplication.class, args);
	}

}
