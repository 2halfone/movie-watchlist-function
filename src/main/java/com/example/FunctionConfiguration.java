package com.example.movie_watchlist_function;

import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.movie_watchlist_function.Movie;
import com.example.movie_watchlist_function.MovieRepository;

@Configuration
public class FunctionConfiguration {

    private final MovieRepository repo;

    public FunctionConfiguration(MovieRepository repo) {
        this.repo = repo;
    }

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> route() {
        return flux -> flux
            .flatMap(repo::save)
            .flatMap(saved -> Mono.just(saved));
    }
}
