package com.qishenghe.hugin.module.formwork;

/**
 * 控制工具模版
 *
 * @author qishenghe
 * @date 11/4/21 11:03 AM
 * @change 11/4/21 11:03 AM by shenghe.qi@relxtech.com for init
 */
public interface CtrlUtil {

    /**
     * 数据转换
     * 
     * @param rule 转换规则
     * @param string 待处理内容
     * @return 转换后结果
     * @since 1.0.0
     * @author qishenghe
     * @date 11/4/21 1:48 PM
     * @change 11/4/21 1:48 PM by shenghe.qi@relxtech.com for init
     */
    String turn (Rule rule, String string);

}
