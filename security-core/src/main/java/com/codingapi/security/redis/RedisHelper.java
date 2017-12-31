//package com.codingapi.security.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by houcunlu on 2017/8/15.
// */
//@Component
//public class RedisHelper {
//
//
//
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
//
//
//    /**
//     * 添加 Token
//     * @param key
//     * @param value
//     * @param seconds
//     * @return
//     */
//    public boolean putToken(String key, String value, int seconds) {
//        ValueOperations<String, String> redis = redisTemplate.opsForValue();
//        redis.set(key,value,seconds, TimeUnit.SECONDS);
//        return true;
//    }
//
//
//    /**
//     * 获取 Token
//     * @param key
//     * @return
//     */
//    public   String getToken(String key) {
//        ValueOperations<String, String> redis = redisTemplate.opsForValue();
//        return redis.get(key);
//    }
//
//
//
//}
