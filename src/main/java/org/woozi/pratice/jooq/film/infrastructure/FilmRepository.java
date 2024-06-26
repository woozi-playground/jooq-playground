package org.woozi.pratice.jooq.film.infrastructure;

import org.jooq.DSLContext;
import org.jooq.generated.tables.ActorEntity;
import org.jooq.generated.tables.FilmActorEntity;
import org.jooq.generated.tables.FilmEntity;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.woozi.pratice.jooq.film.domain.FilmWithActor;
import org.woozi.pratice.jooq.film.domain.SimpleFilmInfo;

import java.util.List;

@Repository
public class FilmRepository {
    private final DSLContext dslContext;
    private final FilmEntity FILM = FilmEntity.FILM;

    public FilmRepository(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Film findById(final Long id) {
        return dslContext.select(FILM.fields())
                .from(FILM)
                .where(FILM.FILM_ID.eq(id))
                .fetchOneInto(Film.class);
    }

    public SimpleFilmInfo findByIdAsSimpleFilmInfo(final Long id) {
        return dslContext.select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION)
                .from(FILM)
                .where(FILM.FILM_ID.eq(id))
                .fetchOneInto(SimpleFilmInfo.class);
    }

    public List<FilmWithActor> findFilmWithActorsList(final Long page, final Long pageSize) {
        final FilmActorEntity FILM_ACTOR = FilmActorEntity.FILM_ACTOR;
        final ActorEntity ACTOR = ActorEntity.ACTOR;
        return dslContext.select(
                        DSL.row(FILM.fields()),
                        DSL.row(FILM_ACTOR.fields()),
                        DSL.row(ACTOR.fields())
                )
                .from(FILM)
                .join(FILM_ACTOR)
                .on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                .join(ACTOR)
                .on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .fetchInto(FilmWithActor.class);
    }

    public List<FilmWithActor> findFilmWithActorsListImplicitPathJoin (Long page, Long pageSize) {
        final FilmActorEntity FILM_ACTOR = FilmActorEntity.FILM_ACTOR;
        return dslContext.select(
                        DSL.row(FILM.fields()),
                        DSL.row(FILM_ACTOR.fields()),
                        DSL.row(FILM_ACTOR.actor().fields())
                )
                .from(FILM)
                .join(FILM_ACTOR)
                .on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .fetchInto(FilmWithActor.class);
    }

    public List<FilmWithActor> findFilmWithActorsListExplicitPathJoin (Long page, Long pageSize) {
        return dslContext.select(
                        DSL.row(FILM.fields()),
                        DSL.row(FILM.filmActor().fields()),
                        DSL.row(FILM.filmActor().actor().fields())
                )
                .from(FILM)
                .join(FILM.filmActor())
                .join(FILM.filmActor().actor())
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .fetchInto(FilmWithActor.class);
    }
}
