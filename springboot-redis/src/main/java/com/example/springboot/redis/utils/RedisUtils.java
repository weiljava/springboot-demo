package com.example.springboot.redis.utils;

import com.example.springboot.redis.annotation.RedisLockKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/17
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class RedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private volatile static RedisUtils redisUtils;

    private static RedissonClient redissonClient;

    public RedisUtils() {
    }

    /**
     * 提供单例模式
     *
     * @return
     */
    public static RedisUtils getInstance() {
        if (redisUtils == null)
            synchronized (RedisUtils.class) {
                if (redisUtils == null) {
                    redisUtils = new RedisUtils();
                }
            }
        return redisUtils;
    }


    /**
     * 使用config创建Redisson
     * Redisson是用于连接Redis Server的基础类
     *
     * @return
     */
    public RedissonClient getRedisson(Config config) {
        RedissonClient redisson = null;
        try {
            redisson = Redisson.create(config);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("成功连接Redis Server");
        return redisson;
    }

    /**
     * 关闭Redisson客户端连接
     *
     * @param redisson
     */
    public void closeRedisson(RedissonClient redisson) {
        redisson.shutdown();
        logger.info("成功关闭Redis Client连接");
    }

    /**
     * 获取字符串对象
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <T> RBucket<T> getRBucket(RedissonClient redisson, String objectName) {
        RBucket<T> bucket = redisson.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取Map对象
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <K, V> RMap<K, V> getRMap(RedissonClient redisson, String objectName) {
        RMap<K, V> map = redisson.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RSortedSet<V> getRSortedSet(RedissonClient redisson, String objectName) {
        RSortedSet<V> sortedSet = redisson.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RSet<V> getRSet(RedissonClient redisson, String objectName) {
        RSet<V> rSet = redisson.getSet(objectName);
        return rSet;
    }

    /**
     * 获取列表
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RList<V> getRList(RedissonClient redisson, String objectName) {
        RList<V> rList = redisson.getList(objectName);
        return rList;
    }

    /**
     * 获取队列
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RQueue<V> getRQueue(RedissonClient redisson, String objectName) {
        RQueue<V> rQueue = redisson.getQueue(objectName);
        return rQueue;
    }

    /**
     * 获取双端队列
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RDeque<V> getRDeque(RedissonClient redisson, String objectName) {
        RDeque<V> rDeque = redisson.getDeque(objectName);
        return rDeque;
    }

    /**
     * 此方法不可用在Redisson 1.2 中
     * 在1.2.2版本中 可用
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RBlockingQueue<V> getRBlockingQueue(RedissonClient redisson, String objectName) {
        RBlockingQueue rb = redisson.getBlockingQueue(objectName);
        return rb;
    }

    /**
     * 获取锁
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public RLock getRLock(RedissonClient redisson, String objectName) {
        RLock rLock = redisson.getLock(objectName);
        return rLock;
    }

    /**
     * 获取原子数
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(RedissonClient redisson, String objectName) {
        RAtomicLong rAtomicLong = redisson.getAtomicLong(objectName);
        return rAtomicLong;
    }


    /**
     * 获取记数锁
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(RedissonClient redisson, String objectName) {
        RCountDownLatch rCountDownLatch = redisson.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     *
     * @param redisson
     * @param objectName
     * @return
     */
    public <M> RTopic<M> getRTopic(RedissonClient redisson, String objectName) {
        RTopic<M> rTopic = redisson.getTopic(objectName);
        return rTopic;
    }


    /**
     * 获取包括方法参数上的key
     * redis key的拼写规则为 "DistRedisLock+" + lockKey + @DistRedisLockKey<br/>
     *
     * @param point
     * @param lockKey
     * @return
     */


    public static String getLockKey(ProceedingJoinPoint point, String lockKey) {
        try {
            lockKey = "DistRedisLock:" + lockKey;
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                MethodSignature methodSignature = (MethodSignature) point.getSignature();
                Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
                SortedMap<Integer, String> keys = new TreeMap<>();
                for (int i = 0; i < parameterAnnotations.length; i++) {
                    RedisLockKey redisLockKey = getAnnotation(RedisLockKey.class, parameterAnnotations[i]);
                    if (redisLockKey != null) {
                        Object arg = args[i];
                        if (arg != null) {
                            keys.put(redisLockKey.order(), arg.toString());
                        }
                    }
                }
                if (keys != null && keys.size() > 0) {
                    for (String key : keys.values()) {
                        lockKey += ":" + key;
                    }
                }
            }

            return lockKey;
        } catch (Exception e) {
            logger.error("getLockKey error.", e);
        }
        return null;
    }

    /**
     * 获取注解类型
     *
     * @param annotationClass
     * @param annotations
     * @param <T>
     * @return
     */
    private static <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Annotation[] annotations) {
        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                if (annotationClass.equals(annotation.annotationType())) {
                    return (T) annotation;
                }
            }
        }
        return null;
    }


    public static RedissonClient createSingleServerClient() {
        if (redissonClient == null) {
            synchronized (RedisUtils.class) {
                if (redissonClient == null) {
                    Config config = new Config();
                    config.useSingleServer().setAddress("redis://10.5.2.247:6379")
                            .setConnectTimeout(30000)
                            .setReconnectionTimeout(10000)
                            .setConnectionPoolSize(20)
                            .setClientName("vote")
                            .setPassword("miguredis");
                    redissonClient = RedisUtils.getInstance().getRedisson(config);
                }
            }
        }
        return redissonClient;
    }


    public static RedissonClient createSentinelServerClient() {
        if (redissonClient == null) {
            synchronized (RedisUtils.class) {
                if (redissonClient == null) {

                    Config config = new Config();
                    config.useSentinelServers()
                            .setMasterName("mymaster")
                            .addSentinelAddress("127.0.0.1:26379", "127.0.0.1:26479", "127.0.0.1:26579")
                            //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
                            .setConnectTimeout(30000)
                            //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。默认:3000
                            .setReconnectionTimeout(10000)
                            //等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
                            .setTimeout(10000)
                            //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
                            .setRetryAttempts(5)
                            //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
                            .setRetryInterval(3000);
                    redissonClient = RedisUtils.getInstance().getRedisson(config);
                }
            }
        }
        return redissonClient;
    }
}