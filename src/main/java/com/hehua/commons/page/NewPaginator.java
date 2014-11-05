package com.hehua.commons.page;

import java.util.Collections;
import java.util.List;

public class NewPaginator<T> {

    private List<T> entries;

    private int pageNumber;

    private int totalCount;

    private int countPerPage;

    private boolean hasPrevious;

    private boolean hasNext;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private final static NewPaginator EMPTY = new NewPaginator(0, 0, Collections.emptyList(), false);

    @SuppressWarnings("unchecked")
    public static <T> NewPaginator<T> emptyPaginator() {
        return EMPTY;
    }

    public static int getPageFromOffsetAndLimit(int offset, int limit) {
        if (offset <= 0) {
            offset = 0;
        }
        if (limit <= 0) {
            limit = 1;
        }
        int quotient = (offset + 1) / limit;
        int remainder = (offset + 1) % limit;
        return quotient + (remainder == 0 ? 0 : 1);
    }

    public final static int TotalCountNotSupported = -1;

    public NewPaginator(int countPerPage, int pageNumber, List<T> entries, boolean hasNext) {
        this.setCountPerPage(countPerPage);
        this.setPageNumber(pageNumber);
        this.setEntries(entries);
        this.setHasPrevious(pageNumber > 1);
        this.setHasNext(hasNext);
        this.setTotalCount(TotalCountNotSupported);
    }

    public NewPaginator(int countPerPage, int pageNumber, List<T> entries, int totalCount) {
        this.setCountPerPage(countPerPage);
        this.setPageNumber(pageNumber);
        this.setEntries(entries);
        this.setHasPrevious(pageNumber > 1);
        this.setTotalCount(totalCount);
        int size = entries.size();
        if (size < countPerPage) {
            this.setHasNext(false);
        } else {
            this.setHasNext(countPerPage * pageNumber < totalCount);
        }
    }

    public NewPaginator(int countPerPage, int pageNumber, List<T> entries, boolean hasPrevious,
            boolean hasNext, int totalCount) {
        super();
        this.pageNumber = pageNumber;
        this.countPerPage = countPerPage;
        this.entries = entries;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.totalCount = totalCount;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageIndex() {
        return pageNumber - 1;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        int totalCount = this.getTotalCount();
        if (totalCount != TotalCountNotSupported) {
            int page = totalCount / this.getCountPerPage();
            page += totalCount - this.getCountPerPage() * page > 0 ? 1 : 0;
            return page;
        } else {
            return TotalCountNotSupported;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            int page = NewPaginator.getPageFromOffsetAndLimit(i, 5);
            System.out.println(i + "," + page);
        }
    }

    @Override
    public String toString() {
        return "Paginator [pageNumber=" + pageNumber + ", countPerPage=" + countPerPage
                + ", entries.size=" + entries.size() + ", hasPrevious=" + hasPrevious
                + ", hasNext=" + hasNext + ", totalCount=" + totalCount + "]";
    }

}
