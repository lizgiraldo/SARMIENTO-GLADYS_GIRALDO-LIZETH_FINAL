package com.backend.clinicaDental;



import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaDentalLgApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaDentalLgApplication.class);

	public static void main(String[] args) {

		LOGGER.info("INICIANDO ARCHIVO LOG ");
		SpringApplication.run(ClinicaDentalLgApplication.class, args);


	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
