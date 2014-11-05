/**
 * 
 */
package com.hehua.commons.page;

import java.util.List;

/**
 * @author zhihua
 *
 */
public class Paginator<T> {

    private int count;

    private List<T> entities;

    private int limit;

    private int offset;

    private boolean hasMore;

    /**
     * @param entities
     * @param limit
     * @param offset
     * @param hasMore
     */
    public Paginator(List<T> entities, int limit, int offset, boolean hasMore) {
        super();
        this.entities = entities;
        this.limit = limit;
        this.offset = offset;
        this.hasMore = hasMore;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Paginator2 [enties=" + entities + ", limit=" + limit + ", offset=" + offset
                + ", hasMore=" + hasMore + "]";
    }

}
