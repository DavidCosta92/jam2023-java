package com.example.JAM23.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args);}


	@Bean
	public OpenAPI swaggerDocsConfig (){
		return new OpenAPI()
				.info(new Info()
						.title("JAM 2023 - Academia")
						.version("1.0")
						.description("MODIFICAR =>  Backend creado en Java 17, Springboot 3 y MySql, para sistema de alumnos................")
						.summary("MODIFICAR =>  PONER URL DESPLIEGUE, AVERIGUAR COMO PONER MAS DE UN CONTACTO")
						.contact(new Contact().email("davidcst2991@gmail.com").name("Costa david").url("https://www.linkedin.com/in/david-costa-yafar/")));
	}

}
