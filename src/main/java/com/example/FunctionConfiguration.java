package com.example;

import java.util.function.Function;
import com.example.Movie;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class FunctionConfiguration {
    @Bean
    public RouterFunction<ServerResponse> route(FunctionCatalog catalog) {
        return RouterFunctions.route()
            .GET("/movies/{id}", request -> {
                String id = request.pathVariable("id");
                @SuppressWarnings("unchecked")
                Function<String, Movie> fn = catalog.lookup(Function.class, "getMovieById");
                Movie movie = fn.apply(id);
                return ServerResponse.ok().bodyValue(movie);
            })
            .GET("/movies", request -> {
                @SuppressWarnings("unchecked")
                Function<Void, java.util.List<Movie>> fnList = catalog.lookup(Function.class, "listMovies");
                java.util.List<Movie> list = fnList.apply(null);
                return ServerResponse.ok().bodyValue(list);
            })
            .POST("/movies", request ->
                request.bodyToMono(Movie.class)
                       .flatMap(movie -> {
                           @SuppressWarnings("unchecked")
                           Function<Movie, Movie> fnCreate = catalog.lookup(Function.class, "createMovie");
                           return Mono.just(fnCreate.apply(movie));
                       })
                       .flatMap(m -> ServerResponse.status(201).bodyValue(m))
            )
            .build();
    }
}