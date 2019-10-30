package cn.xu.master;

import cn.xu.master.uitls.Master;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MasterApplication {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/getinfo")
    public String getNodeInfo(){
        return "服务器:"+port+(Master.isSurvival?"端口为主服务器":"端口为从服务器");

    }

    public static void main(String[] args) {
        SpringApplication.run(MasterApplication.class, args);
    }

}
