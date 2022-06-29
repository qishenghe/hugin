package com.qishenghe.hugin.session;

import com.qishenghe.hugin.banner.HuginBannerPrinter;
import com.qishenghe.hugin.module.util.HuginCtrlUtil;
import com.qishenghe.hugin.core.pack.HuginRulePack;
import com.qishenghe.hugin.module.util.HuginTransUtil;
import lombok.Data;

/**
 * Hugin session
 *
 * @author qishenghe
 * @date 11/4/21 10:48 AM
 * @change 11/4/21 10:48 AM by shenghe.qi@relxtech.com for init
 */
@Data
public class HuginSession {

    /**
     * 规则容器
     */
    private HuginRulePack huginRulePack;

    /**
     * 控制工具
     */
    private HuginCtrlUtil huginCtrlUtil;

    /**
     * 转换工具
     */
    private HuginTransUtil huginTransUtil;

    /**
     * 获取builder
     * 
     * @return builder
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 11:27 AM
     * @change 6/29/22 11:27 AM by shenghe.qi@relxtech.com for init
     */
    public static Builder builder () {
        return new Builder();
    }

    /**
     * session builder
     *
     * @author qishenghe
     * @date 2021/6/5 18:07
     * @change 2021/6/5 18:07 by qishenghe for init
     */
    public static class Builder {

        /**
         * 规则容器
         */
        private HuginRulePack huginRulePack;

        /**
         * 添加规则
         * 
         * @param rulePack 规则容器
         * @return builder
         * @since 1.0.0
         * @author qishenghe
         * @date 6/29/22 11:13 AM
         * @change 6/29/22 11:13 AM by shenghe.qi@relxtech.com for init
         */
        public synchronized Builder setHuginRulePack (HuginRulePack rulePack) {

            this.huginRulePack = rulePack;

            return this;
        }

        /**
         * 创建控制工具实例
         * 
         * @param huginSession session
         * @return instance
         * @since 1.0.0
         * @author qishenghe
         * @date 6/29/22 11:05 AM
         * @change 6/29/22 11:05 AM by shenghe.qi@relxtech.com for init
         */
        private HuginCtrlUtil createHuginCtrlUtil (HuginSession huginSession) {

            HuginCtrlUtil result = new HuginCtrlUtil();
            result.setHuginSession(huginSession);

            return result;
        }

        /**
         * 创建转换工具实例
         * 
         * @param huginSession session
         * @return instance
         * @since 1.0.0
         * @author qishenghe
         * @date 6/29/22 11:06 AM
         * @change 6/29/22 11:06 AM by shenghe.qi@relxtech.com for init
         */
        private HuginTransUtil createHuginTransUtil (HuginSession huginSession) {

            HuginTransUtil result = new HuginTransUtil();
            result.setHuginSession(huginSession);

            return result;
        }

        /**
         * 生成session
         * 
         * @since 1.0.0
         * @author qishenghe
         * @date 6/29/22 11:10 AM
         * @change 6/29/22 11:10 AM by shenghe.qi@relxtech.com for init
         */
        public synchronized HuginSession getOrCreate() {

            // 打印Banner
            HuginBannerPrinter.printBanner();

            HuginSession huginSession = new HuginSession();

            huginSession.setHuginRulePack(this.huginRulePack);

            huginSession.setHuginCtrlUtil(createHuginCtrlUtil(huginSession));

            huginSession.setHuginTransUtil(createHuginTransUtil(huginSession));

            return huginSession;
        }

    }

}
