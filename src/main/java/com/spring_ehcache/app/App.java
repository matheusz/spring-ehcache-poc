package com.spring_ehcache.app;

import com.spring_ehcache.app.config.AppModule;
import com.spring_ehcache.app.config.CacheModule;
import com.spring_ehcache.app.impl.DataAccess;
import com.spring_ehcache.app.impl.filters.BookFilter;
import com.spring_ehcache.app.impl.filters.UserFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String... args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CacheModule.class);
        ctx.register(AppModule.class);
        ctx.refresh();

        DataAccess dataAccess = ctx.getBean(DataAccess.class);

        System.out.println("\n==========Searching User with id 1==========");
        System.out.println("Result:" + dataAccess.searchUser(new UserFilter(1)));

        System.out.println("\n==========Again searching User with id 1, result will be fetched from cache==========");
        System.out.println("Result:" + dataAccess.searchUser(new UserFilter(1)));

        System.out.println("\n==========Searching User with id 2==========");
        System.out.println("Result:" + dataAccess.searchUser(new UserFilter(2)));

        System.out.println("\n===========================================================\n");

        System.out.println("\n==========Searching Book with id 1==========");
        System.out.println("Result:" + dataAccess.searchBookId(new BookFilter("book 1")));

        dataAccess.invalidateBookCache();

        System.out.println("\n==========Again searching Book with id 1, method will execute again due to cache invalidation==========");
        System.out.println("Result:" + dataAccess.searchBookId(new BookFilter("book 1")));

        dataAccess.updateBookCache(new BookFilter("book 1"));

        System.out.println("\n==========Again searching Book with id 1, result will be fetched from updated cache, which invert ids==========");
        System.out.println("Result:" + dataAccess.searchBookId(new BookFilter("book 1")));
    }

}