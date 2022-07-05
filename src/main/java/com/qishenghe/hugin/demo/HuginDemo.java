package com.qishenghe.hugin.demo;

import com.qishenghe.hugin.core.api.HuginFunction;
import com.qishenghe.hugin.core.pack.HuginRulePack;
import com.qishenghe.hugin.core.rule.Rule;
import com.qishenghe.hugin.session.HuginSession;
import com.qishenghe.hugin.util.DesensitizeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author qishenghe
 * @date 11/4/21 8:01 PM
 * @change 11/4/21 8:01 PM by shenghe.qi@relxtech.com for init
 */
public class HuginDemo {


    public static void main(String[] args) throws Exception {

        demo();

    }

    private static void demo () {

        // 生成测试人
        HuginDemoPerson huginDemoPerson1 = new HuginDemoPerson();
        huginDemoPerson1.setName("张三");
        huginDemoPerson1.setMobilePhone("18645151111");
        huginDemoPerson1.setAge(50);

        HuginDemoPerson huginDemoPerson2 = new HuginDemoPerson();
        huginDemoPerson2.setName("李老六");
        huginDemoPerson2.setMobilePhone("18615432222");
        huginDemoPerson2.setAge(40);

        List<HuginDemoPerson> personList = new ArrayList<>();
        personList.add(huginDemoPerson1);
        personList.add(huginDemoPerson2);


        HuginRulePack rulePack = new HuginRulePack();
        // 将年龄转换为虚岁
        rulePack.addRule("transAge", Rule.getInstance(transAge()));

        // 创建 Hugin session
        HuginSession huginSession = HuginSession.builder()
                .setHuginRulePack(rulePack)
                .getOrCreate();

        // 转换前
        System.out.println("=================转换前=================");
        for (HuginDemoPerson single : personList) {
            System.out.println(single.toString());
        }
        // 转换
        huginSession.getHuginTransUtil().trans(personList);

        // 转换后
        System.out.println("=================转换后=================");
        for (HuginDemoPerson single : personList) {
            System.out.println(single.toString());
        }


    }

    /**
     * 人名脱敏函数
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:35 AM
     * @change 6/29/22 11:35 AM by shenghe.qi@relxtech.com for init
     */
    private static HuginFunction getDesPersonName () {
        return (HuginFunction<String, String>) (origin, params) -> DesensitizeUtil.turn(origin, "1", "${length}");
    }

    /**
     * 手机号脱敏函数
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:52 AM
     * @change 6/29/22 11:52 AM by shenghe.qi@relxtech.com for init
     */
    private static HuginFunction getDesMobilePhone () {
        return (HuginFunction<String, String>) (origin, params) -> DesensitizeUtil.turn(origin, "3", "7");
    }

    /**
     * 将年龄处理为虚岁
     * 
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:54 AM
     * @change 6/29/22 11:54 AM by shenghe.qi@relxtech.com for init
     */
    private static HuginFunction transAge () {
        return (HuginFunction<Integer, Integer>) (origin, params) -> origin + 1;
    }

}
