package com.example.springboot.redis.aop;

import com.example.springboot.redis.annotation.RedisLock;
import com.example.springboot.redis.utils.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/17
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
@Aspect
public class RedisLockAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    @Value("${spring.redis.host}:${spring.redis.port}")
    String address;

    @Pointcut("execution(* com.example.springboot.redis.service..*.lock*(..))")
    private void lockMethod() {
    }

    @Around("lockMethod()")
    public Object lock(ProceedingJoinPoint point) throws Throwable {
        RLock lock = null;
        Object object = null;
        logger.info("into Aspect!");
        try {
            RedisLock redisLock = getDistRedisLockInfo(point);
            RedisUtils redisUtils = RedisUtils.getInstance();
            RedissonClient redissonClient = RedisUtils.createSingleServerClient();
            String lockKey = redisUtils.getLockKey(point, redisLock.lockKey());

            lock = redisUtils.getRLock(redissonClient, lockKey);
            if (lock != null) {
                Boolean status = lock.tryLock(redisLock.maxSleepMills(), redisLock.keepMills(), TimeUnit.MILLISECONDS);
                if (status) {
                    object = point.proceed();
                }
            }
        } finally {
            if (lock != null) {
                lock.unlock();
            }
            logger.info("out Aspect!");
        }
        return object;
    }

    private RedisLock getDistRedisLockInfo(ProceedingJoinPoint point) {
        try {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();
            return method.getAnnotation(RedisLock.class);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

}