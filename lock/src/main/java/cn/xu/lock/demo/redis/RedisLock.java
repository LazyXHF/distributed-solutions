package cn.xu.lock.demo.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author ~许小桀
 * @date 2019/11/3 20:26
 * redis实现分布式锁
 */
public class RedisLock {


    private JedisPool jedisPool;
    private String redisLockKey="redis_lock_key";

    public RedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 获取锁
     * @param acquireTimeout 获取锁前的失效时间
     * @param timeout 获取锁后的失效时间
     */
    public String getRedisLock(Long acquireTimeout,Long timeout){
        Jedis conn = null;
        try {
            //1.建立redis连接
            conn = jedisPool.getResource();
            //2.定义redis对应key 的value的作用释放锁value
            String identifierValue = UUID.randomUUID().toString();
            //3.定义获取锁之前的超时时间
            int expire = (int) (timeout/1000);
            //4.定义获取锁之后的过期时间
            //5.使用循环机制保证重复进行尝试获取锁
            Long endTime = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis()<endTime){
                //6.使用setnx命令插入对应的redisKey,如果返回1
                if (conn.setnx(redisLockKey,identifierValue)==1){
                    //设置对应key的有效期
                    conn.expire(redisLockKey,expire);
                    return identifierValue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn!=null){
                conn.close();
            }
        }

        return null;

    }


    /**
     * 释放锁
     * @param redisLockValue 锁的对应value
     */
    public void unRedis(String redisLockValue){
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            //防止其他同步线程进行删除key
            if (conn.get(redisLockKey).equals(redisLockValue)){
                conn.del(redisLockValue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn!=null){
                conn.close();
            }
        }


    }
}
