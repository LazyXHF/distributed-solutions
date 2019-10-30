package cn.xu.lock.demo.zookeeper;


import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author ~许小桀
 * @date 2019/10/24 17:35
 */
public  class ZookeeperDistrbuteLock extends ZookeeperAbstractLock{

    private CountDownLatch countDownLatch = null;

    @Override
    boolean tryLock() {
        try {

            zkClient.createEphemeral(lockPath);
            return true;
        }catch (Exception e){

            return false;
        }
    }

    @Override
    void waitLock() {

        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                    if (countDownLatch !=null){
                        countDownLatch.countDown();
                    }
                }


        };
        zkClient.subscribeDataChanges(lockPath,iZkDataListener);
        if (zkClient.exists(lockPath)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //删除监听
        zkClient.unsubscribeDataChanges(lockPath,iZkDataListener);
    }
}
