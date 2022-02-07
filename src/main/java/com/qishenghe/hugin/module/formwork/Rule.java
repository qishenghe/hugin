package com.qishenghe.hugin.module.formwork;

import com.alibaba.fastjson.JSON;

/**
 * 转换规则模版
 *
 * @author qishenghe
 * @date 11/4/21 11:00 AM
 * @change 11/4/21 11:00 AM by shenghe.qi@relxtech.com for init
 */
public abstract class Rule {

    /**
     * 序列化
     * 
     * @return 序列化结果
     * @since 1.0.0
     * @author qishenghe
     * @date 2/7/22 2:39 PM
     * @change 2/7/22 2:39 PM by shenghe.qi@relxtech.com for init
     */
    public final String toJson () {
        return JSON.toJSONString(this);
    }

}
