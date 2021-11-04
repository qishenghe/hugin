package com.qishenghe.hugin.module.desensitize;

import java.lang.annotation.*;

/**
 * 脱敏指向
 *
 * @author qishenghe
 * @date 11/4/21 6:51 PM
 * @change 11/4/21 6:51 PM by shenghe.qi@relxtech.com for init
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DesensitizePoint {

    /**
     * 脱敏规则Json
     *
     * @return 脱敏规则Json
     */
    String ruleJson();

}
