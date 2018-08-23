package com.rfb.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMP = 3;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService(){
        super();

        attemptsCache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Integer>() {
                public Integer load(String key) {
                    return 0;
                }
            });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key){
        int attemps = 0;

        try{
            attemps = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attemps = 0 ;
        }

        attemps++;
        attemptsCache.put(key, attemps);
    }

    public boolean isBlocked(String key) {
        try{
            return attemptsCache.get(key) >= MAX_ATTEMP;
        } catch (ExecutionException e) {
            return false;
        }
    }

}
