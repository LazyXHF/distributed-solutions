package cn.xu.lock.demo.redis;

/**
 * @author ~许小桀
 * @date 2019/11/3 21:17
 */
public class ThreadRedisLock implements Runnable {
    private LockService lockService;

    public ThreadRedisLock(LockService lockService) {
        this.lockService = lockService;
    }

    @Override
    public void run() {
        lockService.seckill();
    }
}
