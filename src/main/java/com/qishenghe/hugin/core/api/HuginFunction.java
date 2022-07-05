package com.qishenghe.hugin.core.api;

/**
 * 通过实现该接口，生成规则
 *
 * @author qishenghe
 * @date 7/5/22 5:32 PM
 * @change 7/5/22 5:32 PM by shenghe.qi@relxtech.com for init
 */
public interface HuginFunction<T, R> {

    /**
     * 转换函数接口
     * 
     * @param origin 待处理属性
     * @param params 参数集
     * @return 转换结果
     * @since 1.0.0
     * @author qishenghe
     * @date 7/5/22 5:38 PM
     * @change 7/5/22 5:38 PM by shenghe.qi@relxtech.com for init
     */
    R trans (T origin, String... params);

}
