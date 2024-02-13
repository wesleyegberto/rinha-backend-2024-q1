package com.github.wesleyegberto.apitransacoes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wesleyegberto.apitransacoes.clientes.Cliente;
import com.github.wesleyegberto.apitransacoes.clientes.ClientesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class ApiTransacoesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiTransacoesApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		var mapper = builder.build();
		mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
		return mapper;
	}

	// @Bean
	public CommandLineRunner seedClientes(ClientesRepository clientes) {
		return args -> {
			clientes.save(new Cliente(1, 100000, 0));
			clientes.save(new Cliente(2, 80000, 0));
			clientes.save(new Cliente(3, 1000000, 0));
			clientes.save(new Cliente(4, 10000000, 0));
			clientes.save(new Cliente(5, 500000, 0));
		};
	}
}
