package com.algaworks.oswors.api.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper retornaInstanciaModelMapper() {
		return new ModelMapper();
	}
}
