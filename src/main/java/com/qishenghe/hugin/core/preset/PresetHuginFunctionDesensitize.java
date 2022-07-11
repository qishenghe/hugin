package com.qishenghe.hugin.core.preset;

import com.qishenghe.hugin.core.api.HuginFunction;
import com.qishenghe.hugin.module.desensitize.core.DesensitizeCore;

/**
 * 预设函数_脱敏
 *
 * @author qishenghe
 * @date 7/5/22 6:30 PM
 * @change 7/5/22 6:30 PM by shenghe.qi@relxtech.com for init
 */
public class PresetHuginFunctionDesensitize implements HuginFunction {

    /**
     * 脱敏
     */
    private DesensitizeCore desensitizeCore;

    /**
     * default constructor
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 7/11/22 4:20 PM
     * @change 7/11/22 4:20 PM by shenghe.qi@relxtech.com for init
     */
    public PresetHuginFunctionDesensitize() {
        this.desensitizeCore = new DesensitizeCore();
    }

    /**
     * constructor
     * 
     * @param desensitizeStr 脱敏字符
     * @since 1.0.0
     * @author qishenghe
     * @date 7/11/22 4:21 PM
     * @change 7/11/22 4:21 PM by shenghe.qi@relxtech.com for init
     */
    public PresetHuginFunctionDesensitize(String desensitizeStr) {
        this.desensitizeCore = new DesensitizeCore(desensitizeStr);
    }

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

        return this.desensitizeCore.turn(origin.toString(), params);
    }
}
