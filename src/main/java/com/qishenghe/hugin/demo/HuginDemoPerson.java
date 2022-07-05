package com.qishenghe.hugin.demo;

import com.qishenghe.hugin.core.point.HuginPoint;
import com.qishenghe.hugin.core.preset.HuginPresetRule;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author qishenghe
 * @date 6/28/22 7:24 PM
 * @change 6/28/22 7:24 PM by shenghe.qi@relxtech.com for init
 */
@Data
public class HuginDemoPerson implements Serializable {

    /**
     * 姓名
     */
    @HuginPoint(ruleCode = HuginPresetRule.DESENSITIZE, param = {"1", "${length}"})
    private String name;

    /**
     * 手机号
     */
    @HuginPoint(ruleCode = HuginPresetRule.DESENSITIZE, param = {"3", "${length} - 4"})
    private String mobilePhone;

    /**
     * 年龄
     */
    @HuginPoint(ruleCode = "transAge")
    private Integer age;


}
