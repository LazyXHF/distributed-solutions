package cn.xu.lock.demo.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.awt.windows.ThemeReader;

import javax.sql.rowset.JdbcRowSet;
import java.util.concurrent.locks.Lock;

/**
 * @author ~许小桀
 * @date 2019/11/3 21:05
 */
public class LockService {
    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "192.168.43.110", 6379, 3000,"123");
    }

    private RedisLock redisLock = new RedisLock(pool);


    //实现分布式锁
    public void seckill(){

        //1.获取锁
        String identifierValue = redisLock.getRedisLock(5000l,5000l);
        if (identifierValue == null){
            System.out.println(Thread.currentThread().getName()+"获取锁失败.....");
            return;
        }

        System.out.println(Thread.currentThread().getName()+"获取锁成功"+identifierValue);

        //2.释放锁
        redisLock.unRedis(identifierValue);
    }
}
