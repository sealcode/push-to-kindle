package org.sealcode.pushtokindle.api;

/**
 * Created by Maciej on 11.12.2016.
 */

public abstract class KindleData {

    private static final String FREE = "@free.kindle.com";
    private static final String KINDLE = "@kindle.com";
    private static final String IDUOKAN = "@iduokan.com";
    private static final String CHINA = "@kindle.cn";
    private static final String PBSYNC = "@pbsync.com";

    public static String getDomain(String target) {
        String nr = "2";
        if(target.contains(FREE)) nr = "1";
        else if(target.contains(KINDLE)) nr = "2";
        else if(target.contains(IDUOKAN)) nr = "3";
        else if(target.contains(CHINA)) nr = "4";
        else if(target.contains(PBSYNC)) nr = "5";
        return nr;
    }

    public static String getReceiverId(String target) {
        if(target.contains(FREE)) return target.replaceAll(FREE, "");
        else if(target.contains(KINDLE)) return target.replaceAll(KINDLE, "");
        else if(target.contains(IDUOKAN)) return target.replaceAll(IDUOKAN, "");
        else if(target.contains(CHINA)) return target.replaceAll(CHINA, "");
        else if(target.contains(PBSYNC)) return target.replaceAll(PBSYNC, "");
        else return target;
    }

    public static boolean isReceiverValid(String target) {
        return (isValidEmail(target) && (target.contains(FREE) || target.contains(KINDLE) ||
                target.contains(CHINA) || target.contains(IDUOKAN) || target.contains(PBSYNC)));
    }

    public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
