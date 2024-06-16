package org.woozi.pratice.jooq.film.domain;

import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.generated.tables.pojos.FilmActor;

public class FilmWithActor {

    private Film film;

    private FilmActor filmActor;

    private Actor actor;

    private FilmWithActor() {
    }

    public FilmWithActor(final Film film, final FilmActor filmActor, final Actor actor) {
        this.film = film;
        this.filmActor = filmActor;
        this.actor = actor;
    }

    public Long getFilmId() {
        return film.getFilmId();
    }

    public String getFullActorName() {
        return actor.getFirstName() + " " + actor.getLastName();
    }

    public Film getFilm() {
        return film;
    }

    public FilmActor getFilmActor() {
        return filmActor;
    }

    public Actor getActor() {
        return actor;
    }
}