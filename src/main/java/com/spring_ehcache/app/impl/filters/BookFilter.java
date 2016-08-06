package com.spring_ehcache.app.impl.filters;

public class BookFilter {

    private String bookName;

    public BookFilter(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookFilter that = (BookFilter) o;

        return bookName != null ? bookName.equals(that.bookName) : that.bookName == null;

    }

    @Override
    public int hashCode() {
        return bookName != null ? bookName.hashCode() : 0;
    }
}
