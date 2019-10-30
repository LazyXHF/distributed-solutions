package cn.xu.master.uitls;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ~许小桀
 * @date 2019/10/30 18:42
 * 使用zookeeper实现master选举策略
 * */
@Component
public class ElectionMaster  implements ApplicationRunner {
    private ZkClient zkClient = new ZkClient("127.0.0.1:2181");
    private String path = "/myzk";
    @Value("${server.port}")
    private String port;


    //启动后执行该方法
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("---------------------------------ZK启动成功---------------------------------");
        //1.项目启动的时候在zk上创建一个临时节点
        //2.谁能够创建节点谁为主服务器
        createEphemeral();

        //3.使用监听判断节点是否被删除,如被删除则重新创建节点
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("---------------------------------节点发生改变!---------------------------------");
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                //开始重新选取主节点
                createEphemeral();
                System.out.println("---------------------------------节点已删除---------------------------------");

            }
        });

    }

    public void createEphemeral(){
        try {
            zkClient.createEphemeral(path);
            System.out.println("---------------------------------节点创建成功!---------------------------------");
            Master.isSurvival = true;
        }catch (Exception e){
            Master.isSurvival=false;
        }
    }
}
