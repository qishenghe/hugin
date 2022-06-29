package com.qishenghe.hugin.util;

import com.qishenghe.hugin.module.desensitize.core.DesensitizeCore;

import java.util.Properties;

/**
 * 脱敏工具
 *
 * @author qishenghe
 * @date 6/29/22 11:39 AM
 * @change 6/29/22 11:39 AM by shenghe.qi@relxtech.com for init
 */
public class DesensitizeUtil {

    /**
     * 脱敏字符串字长长度占位符
     */
    private static final String DESENSITIZE_STR_LENGTH = "length";

    /**
     * 数据转换
     *
     * @param string 待处理内容
     * @param indexStrArr 下标数组
     * @return 转换后结果
     * @since 1.0.0
     * @author qishenghe
     * @date 11/4/21 1:48 PM
     * @change 11/4/21 1:48 PM by shenghe.qi@relxtech.com for init
     */
    public static String turn(String string, String... indexStrArr) {

        // 获取脱敏规则数组下标序列
        int[] indexArr = analysisManualRule(string, indexStrArr);
        // 执行脱敏处理
        return DesensitizeCore.desensitizeStr(string, indexArr);
    }

    /**
     * 脱敏规则解析
     * @param string 待处理内容
     * @param indexStrArr 下标数组【String】
     * @return 下标数组
     */
    private static int[] analysisManualRule(String string, String... indexStrArr) {
        // 生成占位内容对照
        Properties properties = new Properties();
        // 1.待脱敏字符串长度
        properties.setProperty(DESENSITIZE_STR_LENGTH, string.length() + "");

        // 解析占位符内容
        PlaceholderResolver resolver = PlaceholderResolver.getDefaultResolver();
        String[] newDesensIndex = new String[indexStrArr.length];
        for (int i = 0; i < indexStrArr.length; i++) {
            String newSingleDesensIndex = resolver.resolveByProperties(indexStrArr[i], properties);
            newDesensIndex[i] = newSingleDesensIndex;
        }

        // 计算每一个可能的运算表达式，将计算结果放入新的数组中并返回
        int[] desensIndexInt = new int[newDesensIndex.length];
        // 将解析结果放入新数组
        for (int i = 0; i < newDesensIndex.length; i++) {
            double doubleNum = CalculatorUtil.conversion(newDesensIndex[i]);
            int intNum = (int) doubleNum;
            if (intNum > string.length()) {
                intNum = string.length();
            } else if (intNum < 0) {
                intNum = 0;
            }
            desensIndexInt[i] = intNum;
        }
        return desensIndexInt;
    }

}
