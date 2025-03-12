package example.micronaut;

import example.micronaut.domain.Genre;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Flux;

@Transactional
public class GenreService {

    @Inject
    GenreRepository genreRepository;

    public Flux<Genre> findAll() {
        return genreRepository.findAll();
    }

}
