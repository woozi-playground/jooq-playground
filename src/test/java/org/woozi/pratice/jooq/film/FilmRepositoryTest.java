package org.woozi.pratice.jooq.film;

import org.assertj.core.api.Assertions;
import org.jooq.DSLContext;
import org.jooq.generated.tables.pojos.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.woozi.pratice.jooq.film.api.dto.FilmWithActorPagedResponse;
import org.woozi.pratice.jooq.film.application.FilmService;
import org.woozi.pratice.jooq.film.domain.SimpleFilmInfo;
import org.woozi.pratice.jooq.film.infrastructure.FilmRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FilmRepositoryTest {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @Test
    @DisplayName("1) 영화정보 조회")
    void findById() {
        // When
        final Film film = filmRepository.findById(1L);

        // Then
        assertNotNull(film);
    }

    @Test
    @DisplayName("2) 영화정보 간략 조회")
    void getSimpleFilmInfo() {
        SimpleFilmInfo simpleFilmInfo = filmService.getSimpleFilmInfo(1L);

        assertThat(simpleFilmInfo).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("3) 영화와 배우 정보를 페이징하여 조회. - 응답까지")
    void getFilmActorPageResponse() {
        final FilmWithActorPagedResponse filmActorPageResponse = filmService.getFilmActorPageResponse(1L, 10L);

        assertThat(filmActorPageResponse).extracting("filmActor").asList().isNotEmpty();
    }


}