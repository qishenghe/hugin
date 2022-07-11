package com.qishenghe.hugin.module.desensitize.core;

import com.qishenghe.hugin.util.CalculatorUtil;
import com.qishenghe.hugin.util.PlaceholderResolver;

import java.util.Properties;

/**
 * 脱敏核心
 *
 * @author qishenghe
 * @date 11/4/21 5:28 PM
 * @change 11/4/21 5:28 PM by shenghe.qi@relxtech.com for init
 */
public class DesensitizeCore {

    /**
     * 默认脱敏字符
     */
    private static final String DEFAULT_DESENSITIZE_STR = "*";

    /**
     * 默认脱敏字符串字长长度占位形参
     */
    private static final String DEFAULT_DESENSITIZE_STR_LENGTH = "length";

    /**
     * 脱敏字符
     */
    private String desensitizeStr;

    /**
     * 脱敏字符串字长长度占位形参
     */
    private String desensitizeStrLength;

    /**
     * default constructor
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 7/11/22 4:13 PM
     * @change 7/11/22 4:13 PM by shenghe.qi@relxtech.com for init
     */
    public DesensitizeCore () {
        this.desensitizeStr = DEFAULT_DESENSITIZE_STR;
        this.desensitizeStrLength = DEFAULT_DESENSITIZE_STR_LENGTH;
    }

    /**
     * constructor
     * 
     * @param desensitizeStr 脱敏字符
     * @since 1.0.0
     * @author qishenghe
     * @date 7/11/22 4:14 PM
     * @change 7/11/22 4:14 PM by shenghe.qi@relxtech.com for init
     */
    public DesensitizeCore (String desensitizeStr) {
        this.desensitizeStr = desensitizeStr;
        this.desensitizeStrLength = DEFAULT_DESENSITIZE_STR_LENGTH;
    }

    /**
     * constructor
     *
     * @param desensitizeStr 脱敏字符
     * @since 1.0.0
     * @author qishenghe
     * @date 7/11/22 4:14 PM
     * @change 7/11/22 4:14 PM by shenghe.qi@relxtech.com for init
     */
    public DesensitizeCore (String desensitizeStr, String desensitizeStrLength) {
        this.desensitizeStr = desensitizeStr;
        this.desensitizeStrLength = desensitizeStrLength;
    }

    /**
     * 字符串脱敏
     *
     * @param string   待处理内容
     * @param indexArr 下标数组
     * @return 脱敏结果
     */
    public String desensitizeStr(String string, int... indexArr) {

        if (indexArr.length < 2 || indexArr.length % 2 == 1) {
            // 脱敏位下标集不符合标准，跳过执行
            return string;
        }

        StringBuilder stringBuilder = new StringBuilder(string);
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < indexArr.length; i++) {
            if (i % 2 == 0) {
                startIndex = indexArr[i];
            } else {
                endIndex = indexArr[i];
            }

            if (startIndex * endIndex > 0) {
                // 起止位准备就绪，执行脱敏
                singleDesensitize(stringBuilder, startIndex, endIndex);
                // 脱敏结束，指针归位
                startIndex = 0;
                endIndex = 0;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 脱敏函数字符串处理
     * 注：下标域左闭右开
     *
     * @param str        待处理内容
     * @param startIndex 开始下标
     * @param endIndex   终止下标
     */
    private void singleDesensitize(StringBuilder str, int startIndex, int endIndex) {

        StringBuilder replaceStr = new StringBuilder();

        for (int i = 0; i < endIndex - startIndex; i++) {
            replaceStr.append(this.desensitizeStr);
        }

        str.replace(startIndex, endIndex, replaceStr.toString());
    }

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
    public String turn(String string, String... indexStrArr) {

        // 获取脱敏规则数组下标序列
        int[] indexArr = analysisManualRule(string, indexStrArr);
        // 执行脱敏处理
        return desensitizeStr(string, indexArr);
    }

    /**
     * 脱敏规则解析
     * @param string 待处理内容
     * @param indexStrArr 下标数组【String】
     * @return 下标数组
     */
    private int[] analysisManualRule(String string, String... indexStrArr) {
        // 生成占位内容对照
        Properties properties = new Properties();
        // 1.待脱敏字符串长度
        properties.setProperty(this.desensitizeStrLength, string.length() + "");

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
