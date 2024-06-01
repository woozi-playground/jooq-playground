package org.woozi.pratice.jooq.film.infrastructure;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.generated.tables.ActorEntity;
import org.jooq.generated.tables.FilmActorEntity;
import org.jooq.generated.tables.FilmEntity;
import org.jooq.generated.tables.daos.FilmDao;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.woozi.pratice.jooq.film.domain.FilmWithActors;
import org.woozi.pratice.jooq.film.domain.SimpleFilmInfo;

import java.util.List;

@Repository
public class FilmRepositoryHasA {
    private final FilmDao dao;
    private final DSLContext dslContext;
    private final FilmEntity FILM = FilmEntity.FILM;

    public FilmRepositoryHasA(final Configuration configuration, final DSLContext dslContext) {
        this.dao = new FilmDao(configuration);
        this.dslContext = dslContext;
    }

    public Film findById(Long id) {
        return dao.fetchOneByFilmIdEntity(id);
    }

    public List<Film> fetchRangeOfLengthEntity(final int start, final int end) {
        return dao.fetchRangeOfLengthEntity(start, end);
    }

    public SimpleFilmInfo findByIdAsSimpleFilmInfo(Long id) {
        return dslContext.select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION)
                .from(FILM)
                .where(FILM.FILM_ID.eq(id))
                .fetchOneInto(SimpleFilmInfo.class);
    }

    public List<FilmWithActors> findFilmWithActorsList(Long page, Long pageSize) {
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
                .fetchInto(FilmWithActors.class);
    }
}
