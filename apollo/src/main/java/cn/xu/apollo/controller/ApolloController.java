package cn.xu.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ~许小桀
 * @date 2019/11/2 19:57
 */
@RestController
public class ApolloController {

    @Value("${apollo:default}")
    private String apollo;
    @RequestMapping("apollo")
    public String getApollo(){

        return apollo;

    }
}
