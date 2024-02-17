package com.github.wesleyegberto.apitransacoes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@ImportRuntimeHints({ WebRuntimeHints.class })
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
}
