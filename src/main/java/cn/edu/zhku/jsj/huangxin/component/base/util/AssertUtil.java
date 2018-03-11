package cn.edu.zhku.jsj.huangxin.component.base.util;

import java.util.List;

public class AssertUtil {

    /**
     * 判断对象为空
     * @param obj 对象名
     * @return 对象为空返回true，否则返回false
     */
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }
}
