package com.haohao.sentinel.sentinelbypiespringbootstarter;


import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *  希望通过这个类来写一个 starter 来完成自动加载 SentinelResourceAspect 对象;
 */
@Configuration
public class SentinelAopAutoConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
