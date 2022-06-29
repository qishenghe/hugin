package com.qishenghe.hugin.core.rule;

import java.util.function.Function;

/**
 *
 *
 * @author qishenghe
 * @date 6/29/22 9:23 AM
 * @change 6/29/22 9:23 AM by shenghe.qi@relxtech.com for init
 */
public class CustomRule extends Rule implements Function {

    /**
     * 自定义转换函数
     */
    private Function function;

    /**
     * Constructor
     * 
     * @param function 转换函数
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 10:40 AM
     * @change 6/29/22 10:40 AM by shenghe.qi@relxtech.com for init
     */
    private CustomRule(Function function) {
        super();
        this.function = function;
    }

    /**
     * 获取自定义规则实例
     * 
     * @param function 转换函数
     * @return instance
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 10:41 AM
     * @change 6/29/22 10:41 AM by shenghe.qi@relxtech.com for init
     */
    public static CustomRule getInstance (Function function) {
        return new CustomRule(function);
    }

    /**
     * 转换
     * 
     * @param o 待转换对象
     * @return 转换结果
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 10:45 AM
     * @change 6/29/22 10:45 AM by shenghe.qi@relxtech.com for init
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object apply(Object o) {
        return function.apply(o);
    }

}
