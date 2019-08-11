package com.haohao.sentinel.sentineldemo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelDemoApplication {

    public static void main(String[] args) {
        initFlowRules();
        SpringApplication.run(SentinelDemoApplication.class, args);
    }


    // 1, 设置限流规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello"); // 设置需要监控的资源(资源可以是方法, 接口)
        // 设置限流的类型, 是对 每秒访问数(qps), 还是对线程数 进行限流;
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

}
