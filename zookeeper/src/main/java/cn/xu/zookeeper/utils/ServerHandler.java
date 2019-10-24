package cn.xu.zookeeper.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author ~许小桀
 * @date 2019/10/24 15:09
 */
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter  out = null;
        try {
            in  = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String body = null;
            while (true){
                body = in.readLine();
                if (body==null)
                break;
                System.out.println("receive : "+body);
                out.print("Hello,"+body);

            }

        }catch (Exception e){

            if (in!=null){
                try {
                    in.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }

            if (out !=null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;

            }
        }finally {

        }

    }
}
