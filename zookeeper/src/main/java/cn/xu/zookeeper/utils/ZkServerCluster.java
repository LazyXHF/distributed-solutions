package cn.xu.zookeeper.utils;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author ~许小桀
 * @date 2019/10/31 19:14
 */
public class ZkServerCluster {


    public static void main(String[] args) {
        String connect="192.168.43.110:2181,192.168.43.111:2181,192.168.43.112:2181";
        ZkClient zkClient = new ZkClient(connect);
        zkClient.createPersistent("/test1");
    }



}
