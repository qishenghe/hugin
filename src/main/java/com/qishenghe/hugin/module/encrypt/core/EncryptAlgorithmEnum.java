package com.qishenghe.hugin.module.encrypt.core;

/**
 * 加密算法枚举
 *
 * @author qishenghe
 * @date 11/4/21 6:11 PM
 * @change 11/4/21 6:11 PM by shenghe.qi@relxtech.com for init
 */
public enum EncryptAlgorithmEnum {

    /**
     * BASE64
     */
    BASE64("BASE64"),

    /**
     * MD5
     */
    MD5("MD5");

    /**
     * 加密算法名称
     */
    private String algorithmName;

    EncryptAlgorithmEnum(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    private String getAlgorithmName() {
        return algorithmName;
    }

    private void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
