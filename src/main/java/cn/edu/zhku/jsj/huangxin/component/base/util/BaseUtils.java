package cn.edu.zhku.jsj.huangxin.component.base.util;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;
import cn.edu.zhku.jsj.huangxin.component.base.model.UserPO;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class BaseUtils {

    private static Logger logger = Logger.getRootLogger();

    /**
     * 将实体转成map
     * @param po 实体
     * @return Map<String ,Object> key：字段名，value：字段值
     */
    public static Map<String ,Object> getFieldMap(IBasePO po){
        Class clazz = po.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String , Object> fieldMap = null;
        if(fields.length > 0){
            fieldMap = new HashMap<String , Object>();
            for(Field field : fields){
                Object fieldValue = getFieldValueByName(field.getName(),po);
                if(fieldValue != null){
                    fieldMap.put(getColumnName(field.getName()),fieldValue);
                }
            }
        }
        return fieldMap;
    }

    /**
     * 将实体列表转成map列表
     * @param poList 实体列表
     * @return
     */
    public static List<Map<String ,Object>> getFieldMapList(List<? extends IBasePO> poList){
        List<Map<String ,Object>> fieldMapList = null;
        if( poList!=null && poList.size() > 0){
            Class clazz = poList.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            if(fields.length > 0){
                fieldMapList = new ArrayList<>();
                for(IBasePO basePO : poList){
                    Map<String ,Object> fieldMap = new HashMap<>();
                    for(Field field : fields){
                        Object fieldValue = getFieldValueByName(field.getName(),basePO);
                        if(fieldValue != null){
                            fieldMap.put(getColumnName(field.getName()),fieldValue);
                        }
                    }
                    fieldMapList.add(fieldMap);
                }
            }
        }

        return fieldMapList;
    }

    /**
     * 将map转成实体
     * @param columnMap
     * @param clazz
     */
    public static<T extends IBasePO> T mapToPO(Map<String,Object> columnMap,Class<T> clazz) throws
            IllegalAccessException, InstantiationException {
        T basePO = null;
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length>0){
            basePO = clazz.newInstance();
            for(Field field : fields){
                String fieldName = getColumnName(field.getName());
                if(columnMap.containsKey(fieldName)){
                    field.setAccessible(true);
                    field.set(basePO,columnMap.get(fieldName));
                }
            }
        }
        return basePO;
    }

    //通过字段名获取字段值
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }
    //获取数据库列名（实体属性为驼峰式命名，数据库列名为下划线分隔命名）
    private static String getColumnName(String fieldName) {
        if(fieldName != null){
            char[] chars = fieldName.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char c : chars) {
                if (Character.isUpperCase(c)) {
                    sb.append("_" + Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        /*UserPO userPO = new UserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("huangxin");
        userPO.setPassword("123");
        UserPO userPO2 = new UserPO();
        userPO2.setId(UUID.randomUUID().toString());
        userPO2.setUserName("huangxin2");
        userPO2.setPassword("1232");*/
        /*Map<String , Object> fieldMap = getFieldMap(userPO);
        System.out.println(fieldMap);*/
        /*List<UserPO> userList = new ArrayList<>();
        userList.add(userPO);
        userList.add(userPO2);
        List<Map<String ,Object>> fieldMapList = getFieldMapList(userList);
        System.out.print(fieldMapList);*/
        Map<String , Object> columnMap = new HashMap<>();
        columnMap.put("id","123");
        columnMap.put("user_name","huangxin");
        columnMap.put("password","123123");
        try {
            UserPO userPO = mapToPO(columnMap,UserPO.class);
            System.out.println(userPO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
