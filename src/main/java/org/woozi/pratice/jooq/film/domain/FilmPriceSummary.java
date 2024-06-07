package org.woozi.pratice.jooq.film.domain;

import java.math.BigDecimal;

public class FilmPriceSummary {
    private Long filmId;
    private String title;
    private PriceCategory priceCategory;
    private BigDecimal rentalRate;
    private Long totalInventory;

    public FilmPriceSummary() {
    }

    public FilmPriceSummary(final Long filmId, final String title, final PriceCategory priceCategory, final BigDecimal rentalRate, final Long totalInventory) {
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

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public Long getTotalInventory() {
        return totalInventory;
    }

    public enum PriceCategory {
        CHEAP("Cheap"),
        MODERATE("Moderate"),
        EXPENSIVE("Expensive");

        private final String code;

        PriceCategory(final String code) {
            this.code = code;
        }

        public static PriceCategory findByCode(final String code) {
            for (PriceCategory priceCategory : values()) {
                if (priceCategory.code.equalsIgnoreCase(code)) {
                    return priceCategory;
                }
            }
            throw new IllegalArgumentException("Unknown price category code: " + code);
        }

        public String getCode() {
            return code;
        }
    }
}
