package cn.xu.lock.demo;

import java.text.SimpleDateFormat;

/**
 * @author ~许小桀
 * @date 2019/10/24 17:56
 */
public class Number {
    private static int count=0;


    //使用synchronized 实现单机版本的而线程安全或者使用lock锁
    public synchronized int getId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return  count++;
    }

}
