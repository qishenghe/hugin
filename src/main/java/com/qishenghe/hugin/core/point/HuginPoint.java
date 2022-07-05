package com.qishenghe.hugin.core.point;

import java.lang.annotation.*;

/**
 * Hugin Point
 *
 * @author qishenghe
 * @date 2/7/22 2:28 PM
 * @change 2/7/22 2:28 PM by shenghe.qi@relxtech.com for init
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HuginPoint {

    /**
     * 转换规则编码
     */
    String ruleCode();

    /**
     * 参数
     */
    String[] param() default {};

    /**
     * 转换前内容放置位置（null：默认不保存原code内容）
     */
    String beforeTransCopyTo() default "";

    /**
     * 转换结果放置位置（null：默认放置至当前字段）
     */
    String overTransCopyTo() default "";

}
