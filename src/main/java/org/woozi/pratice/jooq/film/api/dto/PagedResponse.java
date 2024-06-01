package org.woozi.pratice.jooq.film.api.dto;

public class PagedResponse {
    private final long page;
    private final long pageSize;

    public PagedResponse(final long page, final long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public long getPage() {
        return page;
    }

    public long getPageSize() {
        return pageSize;
    }
}