package com.qishenghe.hugin.version;

/**
 * Hugin version
 *
 * @author qishenghe
 * @date 11/4/21 10:22 AM
 * @change 11/4/21 10:22 AM by shenghe.qi@relxtech.com for init
 */
public class HuginVersion {

    /**
     * Constructor
     */
    private HuginVersion () {}

    /**
     * Get Version
     * @return version number
     */
    public static String getVersion () {
        Package pkg = HuginVersion.class.getPackage();
        return pkg != null ? pkg.getImplementationVersion() : null;
    }

}
