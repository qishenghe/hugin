package com.qishenghe.hugin.module.desensitize;

import com.alibaba.fastjson.JSON;
import com.qishenghe.hugin.module.formwork.Rule;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 脱敏规则
 *
 * @author qishenghe
 * @date 11/4/21 11:01 AM
 * @change 11/4/21 11:01 AM by shenghe.qi@relxtech.com for init
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DesensitizeRule extends Rule {

    /**
     * 脱敏规则序列
     */
    private String[] desensitizeRules;

    /**
     * Constructor
     *
     * @param desensitizeRules 脱敏规则序列
     */
    public DesensitizeRule(String... desensitizeRules) {
        this.desensitizeRules = desensitizeRules;
    }

    /**
     * Constructor
     * 注：整型重载
     *
     * @param desensitizeRules 脱敏规则序列
     */
    public DesensitizeRule(int... desensitizeRules) {
        String[] strings = new String[desensitizeRules.length];
        for (int i = 0; i < desensitizeRules.length; i++) {
            strings[i] = desensitizeRules[i] + "";
        }
        this.desensitizeRules = strings;
    }

    /**
     * 根据Json字符串获取对象实例
     *
     * @param json json
     * @return 对象实例
     */
    public static DesensitizeRule byJson(String json) {
        return JSON.parseObject(json, DesensitizeRule.class);
    }

}
