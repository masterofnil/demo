package example.micronaut;

import example.micronaut.domain.Genre;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class GenreRepositoryTest {

    @Inject
    GenreRepository repository;

    @Inject
    GenreService service;

    @Test
    public void testSaveGenre() {
        Genre genre = new Genre();
        genre.setName("test");
        repository.save(genre).block();

        List<Genre> genres = service.findAll().collectList().block();

        assertEquals(1, genres.size());
    }
}