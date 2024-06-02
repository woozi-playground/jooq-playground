package org.woozi.pratice.jooq.actor;

import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;

import java.util.List;

public class ActorFilmography {

    private final Actor actor;
    private final List<Film> filmList;

    public ActorFilmography(Actor actor, List<Film> filmList) {
        this.actor = actor;
        this.filmList = filmList;
    }

    public Actor getActor() {
        return actor;
    }

    public List<Film> getFilmList() {
        return filmList;
    }
}