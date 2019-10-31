package cn.xu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class ProjectAApplication {

    @RequestMapping("/aIndex")
    public String aIndex() {
        return "aIndex";
    }
    public static void main(String[] args) {
        SpringApplication.run(ProjectAApplication.class, args);
    }

}
