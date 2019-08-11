package com.haohao.sentinel.sentineldemo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/***
 *  使用 Sentinel 进行单机限流
 *   Sentinel 限流的方式
 *      1, 设置限流的规则;
 *      2, 对应用进行监控;
 *      3, 监控的结果 使用 sentinel-dashboard 控制台进行展示;
 */
public class SentinelDemo {

    // 1, 设置限流规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("doTest"); // 设置需要监控的资源(资源可以是方法, 接口)
        // 设置限流的类型, 是对 每秒访问数(qps), 还是对线程数 进行限流;
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        // 1 设置限流规则
        initFlowRules();

        //2 程序的业务逻辑
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("doTest");
                System.out.println("Hello World");
            } catch (BlockException e) {
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    entry.exit(); // 释放 entry
                }
            }
        }
    }


}
