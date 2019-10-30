package cn.xu.lock.demo.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.cache.CacheManager;

/**
 * @author ~许小桀
 * @date 2019/10/24 17:16
 * 将重复代码抽象到子类中(模板方法设计模式)
 */
public abstract class ZookeeperAbstractLock implements ExtLock{

    private static final  String CONNECTION="127.0.0.1:2181";
    protected ZkClient zkClient = new ZkClient(CONNECTION);
    protected String lockPath="/lockPath";

    //获取锁
    @Override
    public void getLock() {
        //1.连接zkClient在zk上创建一个临时节点
        //2.如果节点创建成功 直接执行业务逻辑 如果节点创建失败,进行等待
        if (tryLock()){
            System.out.println("-------------获取所资源成功!---------------");
        }else {
            //使用事件监听通知监听节点是否被删除,如果被删除的话重新进入获取所得资源
            waitLock();
            getLock();
        }

    }


    //获取锁
    abstract boolean tryLock();
    //如果锁获取失败,则需要等待
    abstract void waitLock();

    //释放锁
    @Override
    public void unLock() {
        if (zkClient!=null){
            zkClient.close();
        }
    }
}
