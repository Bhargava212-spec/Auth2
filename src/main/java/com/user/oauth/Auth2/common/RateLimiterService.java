package com.user.oauth.Auth2.common;//package com.user.oauth.Auth2.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class RateLimiterService {
//
//    @Value("${redis.requestLimit}")
//    private Integer requestLimit;
//
//    @Value("${redis.timeLimit}")
//    private Integer timeLimit;
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    public boolean isRequestAllowed(String userName) {
//        String key = "user_name" + userName;
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        Long currentCount = valueOperations.increment(key);
//
//        if(1==currentCount.intValue()){
//          redisTemplate.expire(key,timeLimit, TimeUnit.SECONDS);
//        }
//        return currentCount<=requestLimit;
//    }
//
//}
