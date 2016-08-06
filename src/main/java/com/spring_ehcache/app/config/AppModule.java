package com.spring_ehcache.app.config;

import com.spring_ehcache.app.impl.DataAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModule {

    @Bean
    public DataAccess getEmployee() {
        return new DataAccess();
    }

}
