package cn.xu.lock.demo.zookeeper;

/**
 * @author ~许小桀
 * @date 2019/10/24 17:17
 */
public interface ExtLock {
    //获取锁
    void getLock();

    //释放锁
    void unLock();
}
