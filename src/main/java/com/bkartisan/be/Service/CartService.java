package com.bkartisan.be.Service;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Product;

@Service
public class CartService {

    private String ID_PREFIX = "cart:";

    private StringRedisTemplate redisOperations;
    private ProductService productService;

    @Autowired
    public CartService(StringRedisTemplate redisOperations, ProductService productService) {
        this.productService = productService;
        this.redisOperations = redisOperations;
    }

    public Map<String, Integer> getCart(String username) {
        String key = ID_PREFIX + username;
        Map<String, Integer> cart = redisOperations.opsForHash().entries(key).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        entry -> Integer.valueOf(entry.getValue().toString())));
        return cart;
    }

    
    public Integer getTotalPrice(String username) {
        String key = ID_PREFIX + username;
        // No items in cart - return 0
        if (!redisOperations.hasKey(key)) {
            return 0;
        }

        try {
            Map<?, ?> entries = redisOperations.opsForHash().entries(key);

            // First, map to a stream of price of each item. Then sum it up with
            // Collectors.summingInt.
            Integer totalPrice = entries.entrySet().stream().map(e -> {
                Product prod = productService.getProduct(Integer.parseInt(e.getKey().toString()));
                Integer price = prod.getPrice();
                return price * (Integer) Integer.parseInt(e.getValue().toString());
            }).collect(Collectors.summingInt(Integer::intValue));

            return totalPrice;
        } catch (ClassCastException e) {
            System.out.println("Error in getTotalPrice: " + e.getMessage());
            throw e;
        }
    }

    public Integer getTotalItems(String username) {
        String key = ID_PREFIX + username;
        return redisOperations.opsForHash().entries(key).values().stream()
                .collect(Collectors.summingInt(e -> Integer.parseInt(e.toString())));
    }

    // public List<Map<String, Integer>> getCartItems(String username) {
    // return
    // redisOperations.opsForHash().values(username).stream().map(CartItem::new).collect(Collectors.toList());
    // }

    public void addProductToCart(String username, Integer productID, Integer quantity) {
        String key = ID_PREFIX + username;
        if (!redisOperations.hasKey(key)) {
            redisOperations.opsForHash().put(key, productID.toString(),
                    quantity.toString());
            redisOperations.expire(key, Duration.ofDays(7));
        } else if (redisOperations.opsForHash().hasKey(key, productID.toString())) {
            redisOperations.opsForHash().increment(key, productID.toString(), quantity);
        } else {
            redisOperations.opsForHash().put(key, productID.toString(),
                    quantity.toString());
        }
    }
}