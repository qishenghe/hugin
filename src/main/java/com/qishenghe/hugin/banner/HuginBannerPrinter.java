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
    private static final String BANNER_HEAD = "    __  __            _     \n" +
            "   / / / /_  ______ _(_)___ \n" +
            "  / /_/ / / / / __ `/ / __ \\\n" +
            " / __  / /_/ / /_/ / / / / /\n" +
            "/_/ /_/\\__,_/\\__, /_/_/ /_/ \n" +
            "            /____/          ";

    /**
     * BANNER_NAME
     */
    private static final String BANNER_NAME = ":: Hugin ::";

    /**
     * over print
     */
    private static boolean overPrint = false;

    /**
     * Banner printer
     */
    public static void printBanner () {

        if (!overPrint) {
            String version = HuginVersion.getVersion();

            System.out.println(BANNER_HEAD);

            System.out.print("\u001B[36m" + BANNER_NAME + "\u001B[0;39m");
            System.out.print("\t\t");
            System.out.println("\u001B[33m" + "(v" + version + ")" + "\u001B[0;39m");

            overPrint = true;
        }

    }

}
