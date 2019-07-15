package com.example.springboot.redis.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/17
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLockKey {

    /**
     * key的拼接顺序规则
     *     
     */
    int order() default 0;

}