package com.aiyun.cache.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.EvictionAdvisor;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Expiry;
import org.ehcache.spi.service.ServiceConfiguration;

import java.util.Collection;

/**
 * Created by zhaoy on 2016/12/7.
 */
public class CacheConfigurationImpl implements CacheConfiguration {
    @Override
    public Collection<ServiceConfiguration<?>> getServiceConfigurations() {
        return null;
    }

    @Override
    public Class getKeyType() {
        return String.class;
    }

    @Override
    public Class getValueType() {
        return Object.class;
    }

    @Override
    public EvictionAdvisor getEvictionAdvisor() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Expiry getExpiry() {
        Expiry expiry;
        return null;
    }

    @Override
    public ResourcePools getResourcePools() {
        return ResourcePoolsBuilder.heap(100).disk(3, MemoryUnit.MB).build();
    }
}
