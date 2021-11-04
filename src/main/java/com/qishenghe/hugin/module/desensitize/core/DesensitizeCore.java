package com.qishenghe.hugin.module.desensitize.core;

/**
 * 脱敏核心
 *
 * @author qishenghe
 * @date 11/4/21 5:28 PM
 * @change 11/4/21 5:28 PM by shenghe.qi@relxtech.com for init
 */
public class DesensitizeCore {

    /**
     * 脱敏字符
     */
    private static final String DESENSITIZE_STR = "*";

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
    private static void singleDesensitize(StringBuilder str, int startIndex, int endIndex) {

        StringBuilder replaceStr = new StringBuilder();

        for (int i = 0; i < endIndex - startIndex; i++) {
            replaceStr.append(DESENSITIZE_STR);
        }

        str.replace(startIndex, endIndex, replaceStr.toString());
    }



}
