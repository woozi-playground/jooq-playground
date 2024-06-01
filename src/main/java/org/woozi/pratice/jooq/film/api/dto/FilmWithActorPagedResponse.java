package org.woozi.pratice.jooq.film.api.dto;

import org.woozi.pratice.jooq.film.domain.FilmWithActors;

import java.util.List;

public class FilmWithActorPagedResponse {

    private final PagedResponse page;
    private final List<FilmActorResponse> filmActor;

    public FilmWithActorPagedResponse(PagedResponse page, List<FilmWithActors> filmWithActors) {
        this.page = page;
        this.filmActor = filmWithActors.stream()
                .map(FilmActorResponse::new)
                .toList();
    }

    public PagedResponse getPage() {
        return page;
    }

    public List<FilmActorResponse> getFilmActor() {
        return filmActor;
    }

    public static class FilmActorResponse {

        private final String filmTitle;
        private final int filmLength;
        private final String actorFullName;

        public FilmActorResponse(FilmWithActors filmWithActors) {
            this.filmTitle = filmWithActors.getFilm().getTitle();
            this.filmLength = filmWithActors.getFilm().getLength();
            this.actorFullName = filmWithActors.getFullActorName();
        }

        public String getFilmTitle() {
            return filmTitle;
        }

        public int getFilmLength() {
            return filmLength;
        }

        public String getActorFullName() {
            return actorFullName;
        }
    }
}