/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.micronaut;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import example.micronaut.domain.Genre;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.POSTGRES) // <1>
public interface GenreRepository extends ReactorPageableRepository<Genre, Long> { // <2>

    Mono<Genre> save(@NotBlank String name);

    @Transactional
    default Mono<Genre> saveWithException(@NotBlank String name) {
        return save(name)
            .then(Mono.error(new DataAccessException("test exception")));
    }

    Mono<Long> update(@Id long id, @NotBlank String name);
}
