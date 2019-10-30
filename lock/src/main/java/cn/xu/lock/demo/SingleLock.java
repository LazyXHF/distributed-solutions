package cn.xu.lock.demo;

import cn.xu.lock.demo.zookeeper.ExtLock;
import cn.xu.lock.demo.zookeeper.ZookeeperAbstractLock;
import cn.xu.lock.demo.zookeeper.ZookeeperDistrbuteLock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ~许小桀
 * @date 2019/10/24 16:04
 * 单机版本锁的实现
 */
public class SingleLock  implements Runnable{

    Number number = new Number();
    ExtLock lock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        getNumber();
    }

    public  void getNumber(){

        try {
            lock.getLock();
            System.out.println(Thread.currentThread().getName()+"number"+number.getId());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unLock();
        }


    }

    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            Thread thread = new Thread(new SingleLock());
            thread.start();
        }
    }
}
