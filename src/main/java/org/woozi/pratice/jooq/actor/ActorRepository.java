package org.woozi.pratice.jooq.actor;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.generated.tables.ActorEntity;
import org.jooq.generated.tables.FilmActorEntity;
import org.jooq.generated.tables.FilmEntity;
import org.jooq.generated.tables.daos.ActorDao;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static org.woozi.pratice.jooq.util.jooq.JooqListConditionUtils.inIfNotEmpty;
import static org.woozi.pratice.jooq.util.jooq.JooqStringConditionUtils.containsIfNotBlank;

@Repository
public class ActorRepository {
    private final DSLContext dslContext;
    private final ActorDao actorDao;
    private final ActorEntity ACTOR = ActorEntity.ACTOR;

    public ActorRepository(final DSLContext dslContext, final Configuration configuration) {
        this.dslContext = dslContext;
        this.actorDao = new ActorDao(configuration);
    }

    public List<Actor> findByFirstnameAndLastName(String firstName, String lastName) {
        return dslContext.selectFrom(ACTOR)
                .where(
                        ACTOR.FIRST_NAME.eq(firstName),
                        ACTOR.LAST_NAME.eq(lastName)
                ).fetchInto(Actor.class);
    }

    public List<Actor> findByFirstnameOrLastName(String firstName, String lastName) {
        return dslContext.selectFrom(ACTOR)
                .where(
                        ACTOR.FIRST_NAME.eq(firstName).or(ACTOR.LAST_NAME.eq(lastName)),
                        ACTOR.FIRST_NAME.eq(firstName)
                ).fetchInto(Actor.class);
    }

    public List<Actor> findByActorIdIn(List<Long> idList) {
        return dslContext.selectFrom(ACTOR)
                .where(inIfNotEmpty(ACTOR.ACTOR_ID, idList))
                .fetchInto(Actor.class);
    }

    public List<ActorFilmography> findActorFilmography(final ActorFilmographySearchOption searchOption) {
        final FilmActorEntity FILM_ACTOR = FilmActorEntity.FILM_ACTOR;
        final FilmEntity FILM = FilmEntity.FILM;
        final Map<Actor, List<Film>> actorListMap = dslContext.select(
                        DSL.row(ACTOR.fields()).as("actor"),
                        DSL.row(FILM.fields()).as("film")).from(ACTOR
                ).join(FILM_ACTOR)
                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                .join(FILM)
                .on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                .where(
                        containsIfNotBlank(ACTOR.FIRST_NAME.concat(" ").concat(ACTOR.LAST_NAME), searchOption.getActorName()),
                        containsIfNotBlank(FILM.TITLE, searchOption.getFilmTitle())
                )
                .fetchGroups(
                        record -> record.get("actor", Actor.class),
                        record -> record.get("film", Film.class)
                );

        return actorListMap.entrySet().stream()
                .map(entry -> new ActorFilmography(entry.getKey(), entry.getValue()))
                .toList();
    }
}
