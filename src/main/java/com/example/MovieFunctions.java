package com.example;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class MovieFunctions {
    private final MovieRepository repo;

    public MovieFunctions(MovieRepository repo) {
        this.repo = repo;
    }

    // GET /movies/{id}
    public Function<String, Movie> getMovieById() {
        return id -> repo.findById(UUID.fromString(id))
                     .orElseThrow(() -> new RuntimeException("Movie not found: " + id));
    }

    // GET /movies
    public Function<Void, List<Movie>> listMovies() {
        return v -> repo.findAll();
    }

    // POST /movies
    public Function<Movie, Movie> createMovie() {
        return movie -> {
            movie.setId(UUID.randomUUID());
            return repo.save(movie);
        };
    }
}