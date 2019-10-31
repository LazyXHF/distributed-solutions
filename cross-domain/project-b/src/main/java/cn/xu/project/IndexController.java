package cn.xu.project;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ~许小桀
 * @date 2019/10/30 21:46
 */
@RestController
public class IndexController {

    @RequestMapping("ajaxB")
    public Map getInfo(){
        Map map = new HashMap();
        map.put("data","data");
        map.put("errorCode","110");
        return map;
    }

}
