package cn.xu.lock.demo.redis;

/**
 * @author ~许小桀
 * @date 2019/11/3 21:14
 */
public class RedisLockDemo {

    public static void main(String[] args) {
        LockService lockService = new LockService();
        for (int i = 0; i <50 ; i++) {
            new Thread(new ThreadRedisLock(lockService)).start();

        }
    }
}
