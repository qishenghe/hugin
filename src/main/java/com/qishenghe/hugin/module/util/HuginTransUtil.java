package com.qishenghe.hugin.module.util;

import com.qishenghe.hugin.core.point.HuginPoint;
import com.qishenghe.hugin.core.rule.Rule;
import com.qishenghe.hugin.session.HuginSession;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 转换工具
 *
 * @author qishenghe
 * @date 2/7/22 4:34 PM
 * @change 2/7/22 4:34 PM by shenghe.qi@relxtech.com for init
 */
@Data
public class HuginTransUtil {

    /**
     * HuginSession
     */
    private transient HuginSession huginSession;

    /**
     * 【重载】转换
     * 
     * @param result 待转换实体
     * @since 1.0.0
     * @author qishenghe
     * @date 2/7/22 4:58 PM
     * @change 2/7/22 4:58 PM by shenghe.qi@relxtech.com for init
     */
    public <T> void trans(T result) {
        trans(result, new HashMap<>(0));
    }

    /**
     * 【重载】转换
     * 
     * @param resultList 待转换实体集合
     * @since 1.0.0
     * @author qishenghe
     * @date 2/7/22 4:58 PM
     * @change 2/7/22 4:58 PM by shenghe.qi@relxtech.com for init
     */
    public <T> void trans(List<T> resultList) {
        for (T single : resultList) {
            trans(single);
        }
    }

    /**
     * 【重载】转换
     * 
     * @param resultList 待转换实体集合
     * @param ruleMap 规则定义
     * @since 1.0.0
     * @author qishenghe
     * @date 2/7/22 4:58 PM
     * @change 2/7/22 4:58 PM by shenghe.qi@relxtech.com for init
     */
    public <T> void trans(List<T> resultList, Map<String, Rule> ruleMap) {
        for (T single : resultList) {
            trans(single, ruleMap);
        }
    }

    /**
     * 转换
     * 
     * @param result 待转换实体
     * @param ruleMap 规则定义
     * @since 1.0.0
     * @author qishenghe
     * @date 2/7/22 4:59 PM
     * @change 2/7/22 4:59 PM by shenghe.qi@relxtech.com for init
     */
    public <T> void trans(T result, Map<String, Rule> ruleMap) {

        // 判空
        if (result == null) {
            return;
        }
        if (ruleMap == null) {
            ruleMap = new HashMap<>(0);
        }

        // 获取类属性，转Map（key：属性名，value：属性对象）
        Map<String, Field> fieldMap = getObjectFieldMap(result);

        for (String fieldName : fieldMap.keySet()) {
            // 当前属性
            Field field = fieldMap.get(fieldName);
            // rule
            Rule rule = null;
            if (ruleMap.containsKey(fieldName)) {
                // 指向Map中存在该属性
                rule = ruleMap.get(fieldName);
            } else {
                // 指向Map中不存在
                if (field.isAnnotationPresent(HuginPoint.class)) {
                    HuginPoint huginPoint = field.getAnnotation(HuginPoint.class);

                    rule = this.getHuginSession().getHuginRulePack().getRulePack().get(huginPoint.ruleCode());

                }
            }

            try {
                if (rule != null) {
                    // 需要进行转换
                    field.setAccessible(true);
                    // 获取原值
                    Object origin = field.get(result);
                    if (origin != null) {
                        // 获取转换后的值
                        Object overTurn = huginSession.getHuginCtrlUtil().turn(rule, origin);
                        if (field.isAnnotationPresent(HuginPoint.class)) {

                            String beforeTransCopyTo = field.getAnnotation(HuginPoint.class).beforeTransCopyTo();
                            String overTransCopyTo = field.getAnnotation(HuginPoint.class).overTransCopyTo();

                            if (StringUtils.isEmpty(beforeTransCopyTo) && StringUtils.isEmpty(overTransCopyTo)) {
                                // 覆盖原值
                                field.set(result, overTurn);
                            } else {
                                if (StringUtils.isEmpty(beforeTransCopyTo)) {
                                    beforeTransCopyTo = fieldName;
                                }

                                if (StringUtils.isEmpty(overTransCopyTo)) {
                                    overTransCopyTo = fieldName;
                                }

                                Field beforeTransCopyToField = fieldMap.get(beforeTransCopyTo);
                                Field overTransCopyToField = fieldMap.get(overTransCopyTo);
                                // 设为可修改
                                beforeTransCopyToField.setAccessible(true);
                                overTransCopyToField.setAccessible(true);
                                // 顺序修改
                                beforeTransCopyToField.set(result, field.get(result));
                                overTransCopyToField.set(result, overTurn);
                            }

                        } else {
                            // 未找到指向注解，直接覆盖原值
                            field.set(result, overTurn);
                        }
                    }

                }

            } catch (Exception ignored) {
                // 转换时异常，冷处理
            }

        }


    }

    /**
     * 【封装】将对象的属性置入Map中，避免遍历
     *
     * @param object obj
     * @return Map(Obj Name - Obj)
     */
    private static Map<String, Field> getObjectFieldMap(Object object) {
        // 获取所有属性
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null && fields.length != 0) {
            return Arrays.stream(fields).collect(Collectors.toMap(Field::getName, t -> t));
        } else {
            return new HashMap<>(0);
        }
    }

}
