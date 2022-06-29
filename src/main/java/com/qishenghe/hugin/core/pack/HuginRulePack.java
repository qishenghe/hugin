package com.qishenghe.hugin.core.pack;

import com.qishenghe.hugin.core.rule.Rule;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 规则容器
 *
 * @author qishenghe
 * @date 6/29/22 10:07 AM
 * @change 6/29/22 10:07 AM by shenghe.qi@relxtech.com for init
 */
@Data
public class HuginRulePack {

    /**
     * 规则容器
     */
    private Map<String, Rule> rulePack = new HashMap<>(0);

    /**
     * 初始化容器
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:15 AM
     * @change 6/29/22 11:15 AM by shenghe.qi@relxtech.com for init
     */
    private void initPack () {
        this.rulePack = new HashMap<>(0);
    }

    /**
     * 清空容器
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:16 AM
     * @change 6/29/22 11:16 AM by shenghe.qi@relxtech.com for init
     */
    private void clearPack () {
        this.rulePack.clear();
    }

    /**
     * 添加规则
     * 
     * @param key key
     * @param rule 规则
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:17 AM
     * @change 6/29/22 11:17 AM by shenghe.qi@relxtech.com for init
     */
    public void addRule (String key, Rule rule) {

        this.rulePack.put(key, rule);
    }

    /**
     * 添加规则
     * 
     * @param ruleMap 规则map
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:21 AM
     * @change 6/29/22 11:21 AM by shenghe.qi@relxtech.com for init
     */
    public void addRule (Map<String, Rule> ruleMap) {

        this.rulePack.putAll(ruleMap);
    }

    /**
     * 合并规则容器
     * 
     * @param rulePack 规则容器
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:20 AM
     * @change 6/29/22 11:20 AM by shenghe.qi@relxtech.com for init
     */
    public void mergeRulePack (HuginRulePack rulePack) {

        if (rulePack != null && rulePack.getRulePack() != null) {
            this.rulePack.putAll(rulePack.getRulePack());
        }

    }

    /**
     * 合并规则容器
     * 
     * @param rulePacks 规则容器
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:24 AM
     * @change 6/29/22 11:24 AM by shenghe.qi@relxtech.com for init
     */
    public void mergeRulePack (HuginRulePack... rulePacks) {

        if (rulePacks != null && rulePacks.length > 0) {
            for (HuginRulePack singlePack : rulePacks) {
                mergeRulePack(singlePack);
            }
        }
    }

}
