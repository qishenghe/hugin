package com.qishenghe.hugin.module.formwork;

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
     * point规则
     */
    String pointRule();

}
