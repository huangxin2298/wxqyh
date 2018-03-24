package cn.edu.zhku.jsj.huangxin.component.base;

import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinDeptResult;
import cn.edu.zhku.jsj.huangxin.component.base.util.MD5Util;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {
       /* try {
            System.out.println(java.net.URLEncoder.encode("http://threekingstar.xicp.io/wxqyh/portal/index.jsp","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        /*JSONObject jsonObject = JSONObject.parseObject("    {\"errcode\":0,\"errmsg\":\"ok\",\"access_token\":\"uu4nkHcnZxzqAE8szPWkhwgBg3_rC5OMurhYet47p3w99NXs_8XIE3U1OAn7G4CsemtRADbsV2pWwQF-A6Q6dFu9YMG7NdUjSR7zpPn6qLjMjZRgF5JXUUMqewThx7csgDcQby3cWhmurVWvXPBbD_IUG0sJjGM_mNmbrkSkYzhv7v0s62wKRvsQ6t5zEuqGDRqu8fKml-Y50B7MbSfT4A\",\"expires_in\":7200}");
        String access_token = jsonObject.getString("access_token");
        System.out.println(access_token);*/
        /*System.out.println(MD5Util.MD5("test"));//098F6BCD4621D373CADE4E832627B4F6*/
        WeiXinDeptResult weiXinDeptResult = JSONObject.parseObject("{\"errcode\":0,\"errmsg\":\"ok\",\"department\":[{\"id\":2,\"name\":\"广州研发中心\",\"parentid\":1,\"order\":10},{\"id\":3,\"name\":\"邮箱产品部\",\"parentid\":2,\"order\":40}]}", WeiXinDeptResult.class);
        System.out.println(weiXinDeptResult);
    }
}
