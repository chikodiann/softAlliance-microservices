package com.codesofscar.authentication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Authentication microservice REST API Documentation",
				description = "Microservice for Employee Authentication REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Oscar Nwanze",
						email = "nwanzeoscar@gmail.com",
						url = "https://scar-06.github.io/nwanzeoscar/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://scar-06.github.io/nwanzeoscar/"
				)
		)
)
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
