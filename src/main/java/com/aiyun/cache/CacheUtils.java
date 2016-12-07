package com.aiyun.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;
import static org.slf4j.LoggerFactory.getLogger;

public class CacheUtils {

    private static final Logger LOGGER = getLogger(CacheUtils.class);


    private static CacheUtils cacheUtils = null;

    private static CacheManager cacheManager = null;



    private CacheUtils() {

    }

    public static CacheUtils getInstance(){
        if(cacheUtils == null){
            cacheUtils = new CacheUtils();
            cacheManager = newCacheManagerBuilder()
                    .withCache("basicCache",
                            newCacheConfigurationBuilder(String.class, String.class, heap(100).offheap(3, MB)))
                    .build(true);
        }
        return cacheUtils;
    }

    public String getValue(String key){
        Cache<String, String> cache = getCache();
        String value = cache.get(key);
        return value;
    }

    public void setValue(String key,String value){
        Cache<String, String> cache = getCache();
        cache.put(key,value);
    }

    public void remove(String key){
        Cache<String, String> cache = getCache();
        cache.remove(key);
    }

    public CacheUtils getBasicProgrammatic() {
        return cacheUtils;
    }

    public void setBasicProgrammatic(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    private Cache<String, String> getCache() {
        Cache<String,String> cache = null;
        cache = cacheManager.getCache("basicCache", String.class, String.class);
        return cache;
    }
}
