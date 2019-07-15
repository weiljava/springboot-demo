package com.example.springboot.redis.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/17
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 * Description:方法级别加锁
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的key
     * 如果想添加非固定锁，可以在参数上添加@P4jSynKey注解，但是本参数是必写选项<br/>
     * redis key的拼写规则为 "DistRedisLock+" + lockKey + @RedisLOckKey<br/>
     */
    String lockKey();

    /**
     * 持锁时间
     * 单位毫秒,默认5秒<br/>
     */
    long keepMills() default 5 * 1000;

    /**
     * 没有获取到锁时，等待时间
     *
     * @return
     */
    long maxSleepMills() default 120 * 1000;
}