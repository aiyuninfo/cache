package com.aiyun.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
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

    private static final String cacheName = "basicCache";

    private static final int heap = 100;

    private static final int offheap = 3;



    private CacheUtils() {

    }

    public static CacheUtils getInstance(){
        if(cacheUtils == null){
            cacheUtils = new CacheUtils();
            cacheManager = newCacheManagerBuilder()
                    .withCache(cacheName,
                            newCacheConfigurationBuilder(String.class, String.class, heap(heap).offheap(offheap, MB)))
                    .build(true);
        }
        return cacheUtils;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public String getValue(String key){
        Cache<String, String> cache = getCache();
        String value = cache.get(key);
        return value;
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    public void setValue(String key,String value){
        Cache<String, String> cache = getCache();
        cache.put(key,value);
    }

    /**
     * 移除缓存
     * @param key
     */
    public void remove(String key){
        Cache<String, String> cache = getCache();
        cache.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clear(){
        Cache<String, String> cache = getCache();
        cache.clear();
    }

    public CacheUtils getBasicProgrammatic() {
        return cacheUtils;
    }

    public void setBasicProgrammatic(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }

    private Cache<String, String> getCache() {
        Cache<String,String> cache = null;
        cache = cacheManager.getCache(cacheName, String.class, String.class);
        return cache;
    }
}
