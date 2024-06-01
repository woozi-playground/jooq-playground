package org.woozi.pratice.jooq.film.application;

import org.springframework.stereotype.Service;
import org.woozi.pratice.jooq.film.infrastructure.FilmRepository;
import org.woozi.pratice.jooq.film.domain.FilmWithActors;
import org.woozi.pratice.jooq.film.domain.SimpleFilmInfo;
import org.woozi.pratice.jooq.film.api.dto.FilmWithActorPagedResponse;
import org.woozi.pratice.jooq.film.api.dto.PagedResponse;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(final FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public FilmWithActorPagedResponse getFilmActorPageResponse (Long page, Long pageSize) {
        List<FilmWithActors> filmWithActorsList = filmRepository.findFilmWithActorsList(page, pageSize);
        return new FilmWithActorPagedResponse(
                new PagedResponse(page, pageSize),
                filmWithActorsList
        );
    }

    public SimpleFilmInfo getSimpleFilmInfo (Long filmId) {
        return filmRepository.findByIdAsSimpleFilmInfo(filmId);
    }
}