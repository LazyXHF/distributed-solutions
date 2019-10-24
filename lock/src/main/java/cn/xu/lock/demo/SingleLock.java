package cn.xu.lock.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.UUID;

/**
 * @author ~许小桀
 * @date 2019/10/24 16:04
 * 单机版本锁的实现
 */
public class SingleLock  implements Runnable{

    private static int count=0;

    //使用synchronized 实现单机版本的而线程安全或者使用lock锁
    public synchronized static String getId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return  simpleDateFormat.format(new Date())+count++;
    }

    @Override
    public void run() {
        getNumber();
    }

    public    void getNumber(){
        System.out.println(Thread.currentThread().getName()+"number"+getId());
    }

    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            Thread thread = new Thread(new SingleLock());
            thread.start();
        }
    }
}
