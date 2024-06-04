package com.backend.clinicaDental;

import com.backend.clinicaDental.dbconnection.H2Connection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaDentalLgApplication {

	public static void main(String[] args) {

		H2Connection.ejecutarScriptInicial();
		SpringApplication.run(ClinicaDentalLgApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
