package com.qishenghe.hugin;

import com.qishenghe.hugin.module.desensitize.DesensitizeCtrlUtil;
import com.qishenghe.hugin.module.desensitize.DesensitizeRule;

/**
 *
 *
 * @author qishenghe
 * @date 11/4/21 8:01 PM
 * @change 11/4/21 8:01 PM by shenghe.qi@relxtech.com for init
 */
public class Test {

    public static void main(String[] args) {

        long timeStamp1 = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i ++) {
            String str = "齐晟贺";
            DesensitizeCtrlUtil util = new DesensitizeCtrlUtil();
            String result = util.turn(new DesensitizeRule("1", "${length}"), str);
//            System.out.println();
        }
        long timeStamp2 = System.currentTimeMillis();

        System.out.println((timeStamp2 - timeStamp1) / 1);
    }

}
