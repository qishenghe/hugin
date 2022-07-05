package com.qishenghe.hugin.core.rule;

import com.qishenghe.hugin.core.api.HuginFunction;
import lombok.Data;

/**
 * 规则
 *
 * @author qishenghe
 * @date 7/5/22 5:41 PM
 * @change 7/5/22 5:41 PM by shenghe.qi@relxtech.com for init
 */
@Data
public class Rule {

    /**
     * 转换函数
     */
    private HuginFunction huginFunction;

    /**
     * get instance
     * 
     * @param huginFunction huginFunction
     * @return instance
     * @since 1.0.0
     * @author qishenghe
     * @date 7/5/22 6:39 PM
     * @change 7/5/22 6:39 PM by shenghe.qi@relxtech.com for init
     */
    public static Rule getInstance (HuginFunction huginFunction) {

        Rule rule = new Rule();
        rule.setHuginFunction(huginFunction);

        return rule;
    }

}
