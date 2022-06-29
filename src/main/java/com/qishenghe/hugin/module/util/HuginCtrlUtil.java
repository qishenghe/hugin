package com.qishenghe.hugin.module.util;

import com.qishenghe.hugin.core.rule.CustomRule;
import com.qishenghe.hugin.core.rule.Rule;
import com.qishenghe.hugin.session.HuginSession;
import lombok.Data;

/**
 * 控制工具
 *
 * @author qishenghe
 * @date 2/7/22 4:34 PM
 * @change 2/7/22 4:34 PM by shenghe.qi@relxtech.com for init
 */
@Data
public class HuginCtrlUtil {

    /**
     * HuginSession
     */
    private transient HuginSession huginSession;

    /**
     * 数据转换
     *
     * @param rule 转换规则
     * @param object 待处理内容
     * @return 转换后结果
     * @since 1.0.0
     * @author qishenghe
     * @date 11/4/21 1:48 PM
     * @change 11/4/21 1:48 PM by shenghe.qi@relxtech.com for init
     */
    public Object turn (Rule rule, Object object) {

        Object result = object;

        if (rule instanceof CustomRule) {
            result = ((CustomRule) rule).apply(object);
        }

        return result;
    }

}
