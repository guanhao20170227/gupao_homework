package com.haohao.sentinel.sentineldemo.autoconfiguration;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @SentinelResource("sayHello") // 指定限流的资源, 方法级别的限流
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello  World";
    }
}
