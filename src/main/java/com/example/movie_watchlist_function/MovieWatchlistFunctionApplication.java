package com.example.movie_watchlist_function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = ContextFunctionCatalogAutoConfiguration.class)
@ComponentScan(basePackages = "com.example")
@EntityScan("com.example")
@EnableJpaRepositories("com.example")
public class MovieWatchlistFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieWatchlistFunctionApplication.class, args);
	}

}
