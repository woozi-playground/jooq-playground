package org.woozi.pratice.jooq.actor;

public class ActorFilmographySearchOption {

    private final String actorName;
    private final String filmTitle;

    public ActorFilmographySearchOption(final String actorName, final String filmTitle) {
        this.actorName = actorName;
        this.filmTitle = filmTitle;
    }

    public String getActorName() {
        return actorName;
    }

    public String getFilmTitle() {
        return filmTitle;
    }
}