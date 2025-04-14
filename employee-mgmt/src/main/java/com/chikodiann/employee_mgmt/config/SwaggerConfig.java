package com.chikodiann.employee_mgmt.config;

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
                title = "Employee Management Microservice API",
                description = "Microservice for managing employees and departments in a distributed system",
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
                .apis(RequestHandlerSelectors.basePackage("com.chikodiann.employee_mgmt"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("Employee Management Microservice API")
                        .description("This microservice handles employee management operations such as CRUD on employees, departments, and roles.")
                        .version("v1")
                        .contact(new springfox.documentation.service.Contact("Chikodinaka Ann Anyanwu", "https://github.com/chikodiann", "chikodiann@gmail.com"))
                        .license("Apache 2.0")
                        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .build());
    }
}
