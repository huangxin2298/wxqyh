package cn.edu.zhku.jsj.huangxin.component.base;

import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) {
        try {
            System.out.println(java.net.URLEncoder.encode("http://threekingstar.xicp.io/wxqyh/portal/index.jsp","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
