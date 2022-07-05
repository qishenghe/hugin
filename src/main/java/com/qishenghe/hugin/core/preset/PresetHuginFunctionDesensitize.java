package com.qishenghe.hugin.core.preset;

import com.qishenghe.hugin.core.api.HuginFunction;
import com.qishenghe.hugin.util.DesensitizeUtil;

/**
 * 预设函数_脱敏
 *
 * @author qishenghe
 * @date 7/5/22 6:30 PM
 * @change 7/5/22 6:30 PM by shenghe.qi@relxtech.com for init
 */
public class PresetHuginFunctionDesensitize implements HuginFunction {

    /**
     * 脱敏函数
     * 
     * @param origin 原
     * @param params 参数集
     * @return result
     * @since 1.0.0
     * @author qishenghe
     * @date 7/5/22 6:35 PM
     * @change 7/5/22 6:35 PM by shenghe.qi@relxtech.com for init
     */
    @Override
    public Object trans(Object origin, String... params) {

        return DesensitizeUtil.turn(origin.toString(), params);
    }
}
