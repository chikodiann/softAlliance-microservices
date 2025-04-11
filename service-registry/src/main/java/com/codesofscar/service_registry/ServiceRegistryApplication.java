package com.codesofscar.service_registry;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@OpenAPIDefinition(
		info = @Info(
				title = "Discovery Service microservice REST API Documentation",
				description = "Microservice for service registry REST API Documentation",
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
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
