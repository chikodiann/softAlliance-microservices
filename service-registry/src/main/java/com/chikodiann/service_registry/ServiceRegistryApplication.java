package com.chikodiann.service_registry;

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
				title = "Discovery Service Microservice REST API Documentation",
				description = "Microservice responsible for service registration and discovery using Eureka",
				version = "v1",
				contact = @Contact(
						name = "Chikodinaka Ann Anyanwu",
						email = "chikodiann@gmail.com",
						url = "https://github.com/chikodiann"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		)

)
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
