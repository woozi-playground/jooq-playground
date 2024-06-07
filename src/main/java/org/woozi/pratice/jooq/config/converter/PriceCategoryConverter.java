package org.woozi.pratice.jooq.config.converter;

import org.jooq.impl.EnumConverter;
import org.woozi.pratice.jooq.film.domain.FilmPriceSummary;

import java.util.function.Function;

public class PriceCategoryConverter extends EnumConverter<String, FilmPriceSummary.PriceCategory> {
    public PriceCategoryConverter() {
        super(String.class, FilmPriceSummary.PriceCategory.class,  FilmPriceSummary.PriceCategory::getCode);
    }
}
