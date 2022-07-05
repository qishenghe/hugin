package com.qishenghe.hugin.core.rule;

import lombok.Data;

/**
 * 规则导向_携带参数
 *
 * @author qishenghe
 * @date 7/5/22 7:03 PM
 * @change 7/5/22 7:03 PM by shenghe.qi@relxtech.com for init
 */
@Data
public class RulePoint {

    /**
     * 规则
     */
    private Rule rule;

    /**
     * 参数列表
     */
    private String[] params;

}
