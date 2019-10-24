package cn.xu.zookeeper.utils;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ~许小桀
 * @date 2019/10/24 15:00
 * Zookeeper 服务端
 */
public class ZkServerSocket implements Runnable {

    private  static   int  port = 18080;

    public static void main(String[] args) {
        ZkServerSocket serverSocket = new ZkServerSocket(port);
        Thread thread = new Thread(serverSocket);
        thread.start();
    }

    public ZkServerSocket(int port){
        this.port = port;
    };


    //启动注册服务
    public void  regServer() {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",6000,1000);
        String path = "/test";
        if (zkClient.exists(path)){
            zkClient.delete(path);
        }
        //创建临时节点
        zkClient.createEphemeral(path,"127.0.0.1"+port);
    }

    @Override
    public void run() {

        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            regServer();
            System.out.println("Server start port:"+port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }catch (Exception E){
            E.printStackTrace();
        }finally {
            if (serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
