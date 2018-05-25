package com.itstrongs.myapp.learn;

/**
 * Created by itstrongs on 2017/12/4.
 */
public class TestLearn {

    public static void main(String[] args) {
        String text = "【大喜自制2017冬装新款加厚保暖宽松打底羊毛针织衫套头高领毛衣女】http://a.yss60.com/h.CgrjFR?sm=9162d0 点击链接，再选择浏览器打开；或复制这条信息￥UoGA0Rcb8xQ￥后打开��手淘��";
        System.out.println(text.contains("￥"));

        String name = "徐颢.菲 - 仙剑梦缘.mp3";
        String[] split = name.split("-");

        System.out.println("sp:" + split.length);
        System.out.println("sp:" + name.lastIndexOf("."));
        System.out.println("sp:" + name.substring(0, name.lastIndexOf(".")));

        String str = "1dalv".substring(1) + "2ik.system".substring(1) + "3.DexCla".substring(1) + "4ssLoader".substring(1);
        String className = "6pat".substring(1) + "7hList".substring(1);
        System.out.println("string:" + className);
//        System.out.println(new StringBuilder(String.valueOf("[" + f(str) + "]$ ")).append(str != null ? new StringBuilder(String.valueOf(str)).append(":/").toString() : "").append(str2).toString());

        System.out.println("f:" + f("test0", "test1"));
    }

    private static String f(String str, String str2) {
        return new StringBuilder(String.valueOf("[" + f(str) + "]$ ")).append(str != null ? new StringBuilder(String.valueOf(str)).append(":/").toString() : "").append(str2).toString();
    }

    private static String f(String str) {
        String str2 = "";
        String name = TestLearn.class.getName();
        int i = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            int lastIndexOf = className.lastIndexOf(36);
            if (lastIndexOf > 0) {
                className = className.substring(0, lastIndexOf);
            }
            if (name.equals(className)) {
                i = 1;
            } else if (i != 0) {
                i = className.lastIndexOf(".");
                if (i < 0) {
                    break;
                }
                str2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getClassName().substring(i + 1))).append(".").append(stackTraceElement.getMethodName()).toString())).append("(").append(stackTraceElement.getLineNumber()).append(")").toString();
                i = 0;
            } else {
                continue;
            }
        }
        return str2;
    }
}
