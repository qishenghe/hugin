package com.qishenghe.hugin.module.formwork;

import java.util.List;
import java.util.Map;

/**
 * 转换工具模版
 *
 * @author qishenghe
 * @date 11/4/21 11:04 AM
 * @change 11/4/21 11:04 AM by shenghe.qi@relxtech.com for init
 */
public interface TransUtil {

    <T> void trans (T data);

    <T> void trans (List<T> data);

    <T> void trans (T data, Map<String, Rule> ruleMap);

    <T> void trans (List<T> data, Map<String, Rule> ruleMap);

}
