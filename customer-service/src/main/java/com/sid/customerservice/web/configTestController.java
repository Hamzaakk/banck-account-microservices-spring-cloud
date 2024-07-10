package com.sid.customerservice.web;

import config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class configTestController {
    @Value("${global.params.a}")
    private int a;
    @Value("${global.params.b}")
    private int b;
    @Value("${customer.params.x}")
    private int x;
    @Value("${customer.params.y}")
    private int y;

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/testConfig")
    public Map<String,Integer> conffigTest() {
        return Map.of("a",a,"b",b,"x",x,"y",y);
    }

    @GetMapping("/globalConfig")
    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

}
