package com.qishenghe.hugin.banner;

import com.qishenghe.hugin.version.HuginVersion;

/**
 * Banner printer
 *
 * @author qishenghe
 * @date 11/4/21 10:50 AM
 * @change 11/4/21 10:50 AM by shenghe.qi@relxtech.com for init
 */
public class HuginBannerPrinter {

    /**
     * BANNER_HEAD
     */
    private static final String BANNER_HEAD = "Hugin Banner";

    /**
     * BANNER_NAME
     */
    private static final String BANNER_NAME = ":: Hugin ::";

    /**
     * Banner printer
     */
    public static void printBanner () {

        String version = HuginVersion.getVersion();

        System.out.println(BANNER_HEAD);

        System.out.print("\u001B[36m" + BANNER_NAME + "\u001B[0;39m");
        System.out.print("\t\t");
        System.out.println("\u001B[33m" + "(v" + version + ")" + "\u001B[0;39m");

    }

}
