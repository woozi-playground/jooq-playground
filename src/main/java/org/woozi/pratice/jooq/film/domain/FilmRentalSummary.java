package org.woozi.pratice.jooq.film.domain;

import java.math.BigDecimal;

public class FilmRentalSummary {
    private Long filmId;
    private String title;
    private BigDecimal averageRentalDuration;

    public Long getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAverageRentalDuration() {
        return averageRentalDuration;
    }
}