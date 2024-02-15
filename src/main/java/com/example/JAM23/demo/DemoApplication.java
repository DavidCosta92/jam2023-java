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
						.description("Aplicación web, para la gestión de inscripciones, asistencias y usuarios académicos, como parte del evento JAM 2023 para crear soluciones de software gratuitas para emprendedores. A partir de una problemática propuesta, se analizó posibles soluciones que llevaron a diseñar e implementar un MVP, usando Java, Spring, Spring security, MySQL, PostgreSQL, React Js, Swagger, Figma, Github, Notion, etc.\n" +
								"\n" +
								"Aplicacion desplegada en Railway\n" +
								"\n" +
								"Repositorio => https://github.com/DavidCosta92/jam2023-java\n" +
								"Documentacion => https://tense-receipt-production.up.railway.app/docs/swagger-ui/index.html\n" +
								"Deploy de Api => https://tense-receipt-production.up.railway.app/course/")
						.summary("Deploy: https://tense-receipt-production.up.railway.app/course/")
						.contact(new Contact().email("davidcst2991@gmail.com").name("Costa david").url("https://www.linkedin.com/in/david-costa-yafar/")));
	}

}
