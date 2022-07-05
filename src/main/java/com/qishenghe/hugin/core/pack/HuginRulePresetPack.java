package com.qishenghe.hugin.core.pack;

import com.qishenghe.hugin.core.preset.HuginPresetRule;
import com.qishenghe.hugin.core.preset.PresetHuginFunctionDesensitize;
import com.qishenghe.hugin.core.rule.Rule;

import java.util.HashMap;
import java.util.Map;

/**
 * hugin 内置预设规则容器
 *
 * @author qishenghe
 * @date 7/5/22 7:15 PM
 * @change 7/5/22 7:15 PM by shenghe.qi@relxtech.com for init
 */
public class HuginRulePresetPack {

    /**
     * 预设规则容器
     */
    public static Map<String, Rule> rulePack = new HashMap<>(1);

    /**
     * 初始化
     */
    static {
        rulePack.put(HuginPresetRule.DESENSITIZE, Rule.getInstance(new PresetHuginFunctionDesensitize()));
    }

}
