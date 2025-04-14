package com.chikodiann.api_gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Gateway Microservice",
                description = "API Gateway microservice for routing and security for Employee Management services",
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
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chikodiann.api_gateway"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("API Gateway Microservice")
                        .description("The API Gateway handles routing and security for all microservices.")
                        .version("v1")
                        .contact(new springfox.documentation.service.Contact("Chikodinaka Ann Anyanwu", "https://github.com/chikodiann", "chikodiann@gmail.com"))
                        .license("Apache 2.0")
                        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .build());
    }
}
