package com.database.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger"))
public class MdSpringDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdSpringDbApplication.class, args);
	}

}
