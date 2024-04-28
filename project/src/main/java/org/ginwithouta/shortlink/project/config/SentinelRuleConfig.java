package org.ginwithouta.shortlink.project.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.ginwithouta.shortlink.project.common.constant.SentinelConstant.SENTINEL_CREATE_RULE_NAME;

/**
 * @author Ginwithouta
 * Generate at 2024/2/16
 * 初始化限流配置
 */
public class SentinelRuleConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule createOrderRule = new FlowRule();
        createOrderRule.setResource(SENTINEL_CREATE_RULE_NAME);
        // 根据 QPS 进行限制
        createOrderRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置接口的访问上限
        createOrderRule.setCount(100);
        rules.add(createOrderRule);
        FlowRuleManager.loadRules(rules);
    }
}
