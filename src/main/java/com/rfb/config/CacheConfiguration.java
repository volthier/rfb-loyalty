package com.rfb.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.rfb.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.rfb.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.rfb.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.rfb.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbLocation.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbLocation.class.getName() + ".rfbEvents", jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbEvent.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbEvent.class.getName() + ".rfbEvAtts", jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbEvAtt.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.rfb.domain.RfbUser.class.getName() + ".rfbEvAtts", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
