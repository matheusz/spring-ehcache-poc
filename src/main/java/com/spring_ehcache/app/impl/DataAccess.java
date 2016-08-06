package com.spring_ehcache.app.impl;

import com.spring_ehcache.app.impl.filters.BookFilter;
import com.spring_ehcache.app.impl.filters.UserFilter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class DataAccess {

    @Cacheable(value = "userCache")
    public String searchUser(UserFilter userFilter) {
        System.out.println("=========================INSIDE SEARCH USER METHOD=========================");
        if (userFilter.getId() == 1) return "Admin";
        else return "Other user";
    }

    @Cacheable(value = "bookCache")
    public Integer searchBookId(BookFilter bookFilter) {
        System.out.println("=========================INSIDE SEARCH BOOK METHOD=========================");
        if (bookFilter.getBookName().equals("book 1")) return 1;
        else return 2;
    }

    @CacheEvict(value = "bookCache", allEntries = true)
    public void invalidateBookCache() {
    }

    @CachePut(value = "bookCache", key = "'searchBookId_' + #bookFilter.bookName.hashCode()")
    public Integer updateBookCache(BookFilter bookFilter) {
        if (bookFilter.getBookName().equals("book 1")) return 2;
        else return 1;
    }

}