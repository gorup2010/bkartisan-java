package com.bkartisan.be.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private RedisOperations<String, Map<String, Integer>> redisOperations;

    @Autowired
    public CartService(RedisOperations<String, Map<String, Integer>> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public Map<?,?> getCart(String username) {
        return redisOperations.opsForHash().entries(username);
    }

    public Integer getTotalPrice(String username) {
        String entry = "cart:" + username;
        return redisOperations.opsForHash().values(username).stream().mapToInt(Integer::intValue).sum();
    }

    public Integer getTotalItems(String username) {
        String entry = "cart:" + username;
        return redisOperations.opsForHash().entries(entry).size();
    }
}
