package com.spring_ehcache.app.config;

import com.spring_ehcache.app.cache.KeyGeneratorByMethodName;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCaching
public class CacheModule extends CachingConfigurerSupport {

    @Bean
    public List<CacheConfiguration> ehCacheConfiguration() {
        CacheConfiguration userCache = new CacheConfiguration("userCache", 0)
                .maxBytesLocalHeap(100, MemoryUnit.MEGABYTES)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .memoryStoreEvictionPolicy("LRU")
                .statistics(true);

        CacheConfiguration bookCache = new CacheConfiguration("bookCache", 0)
                .maxBytesLocalHeap(100, MemoryUnit.MEGABYTES)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .memoryStoreEvictionPolicy("LRU")
                .statistics(true);
        return Arrays.asList(userCache, bookCache);
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(List<CacheConfiguration> ehCacheConfiguration) {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        ehCacheConfiguration.stream().forEach(configuration::cache);
        return new net.sf.ehcache.CacheManager(configuration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager(ehCacheConfiguration()));
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGeneratorByMethodName();
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        SimpleCacheResolver simpleCacheResolver = new SimpleCacheResolver();
        simpleCacheResolver.setCacheManager(cacheManager());
        return simpleCacheResolver;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

}
