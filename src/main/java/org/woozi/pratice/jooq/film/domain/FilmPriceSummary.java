package org.woozi.pratice.jooq.film.domain;

import java.math.BigDecimal;

public class FilmPriceSummary {
    private Long filmId;
    private String title;
    private String priceCategory;
    private BigDecimal rentalRate;
    private Long totalInventory;

    public FilmPriceSummary() {
    }

    public FilmPriceSummary(final Long filmId, final String title, final String priceCategory, final BigDecimal rentalRate, final Long totalInventory) {
        this.filmId = filmId;
        this.title = title;
        this.priceCategory = priceCategory;
        this.rentalRate = rentalRate;
        this.totalInventory = totalInventory;
    }

    public Long getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public Long getTotalInventory() {
        return totalInventory;
    }
}
