package com.magmutual.userapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MagMutual User API",
                version = "1.0",
                description = "API for retrieving and searching user information"
        )
)
public class UserapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserapiApplication.class, args);
	}

}