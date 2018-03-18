package cn.edu.zhku.jsj.huangxin.component.base.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    private static RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
    /**
     * 指定存活时间
     * @param key 键名
     * @param time 存活时间（s）
     * @return boolean 成功返回true，否则false
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 获取键的存活时间
     * @param key 键名
     * @return long 秒数
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断键是否存在
     * @param key 键名
     * @return boolean 存在返回true，否则返回false
     */

    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除键
     * @param key 键名
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 获取键值
     * @param key 键名
     * @return Object 键值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置
     * @param key 键名
     * @param value 键值
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean set(String key, Object value) {
        try {
            System.out.println(redisTemplate);
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设值（带存活时间）
     * @param key 键名
     * @param value 键值
     * @param time 存活时间（s）
     * @return
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 键值增大
     * @param key 键名
     * @param delta 增加幅度
     * @return
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 键值减少
     * @param key 键名
     * @param delta 减少幅度
     * @return
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 获取hash表对应的指定项值
     * @param key 键名
     * @param item 项
     * @return Object 值
     */
    public static Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash表对应的所有项值
     * @param key 键名
     * @return
     */
    public static Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置hash
     * @param key 键名
     * @param map 键值对
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置hash表（带存活时间）
     * @param key 键名
     * @param map 键值对map
     * @param time 存活时间
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *设置hash表的某一项的值，若项不存在则创建
     * @param key 键名
     * @param item 项
     * @param value 值
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置hash某一项的值，若不存在则创建（带存活时间）
     * @param key 键名
     * @param item 项
     * @param value 值
     * @param time 存活时间
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表的项值
     * @param key 键名
     * @param item 项
     */
    public static void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否存在项
     * @param key 键名
     * @param item 项
     * @return boolean 存在返回true，否则返回false
     */
    public static boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 增大hash的项值
     * @param key 键名
     * @param item 项
     * @param incrNum 增大幅度
     * @return
     */
    public static double hincr(String key, String item, double incrNum) {
        return redisTemplate.opsForHash().increment(key, item, incrNum);
    }

    /**
     * 减少hash的项值
     * @param key 键名
     * @param item 项
     * @param decrNum 减少幅度
     * @return
     */
    public static double hdecr(String key, String item, double decrNum) {
        return redisTemplate.opsForHash().increment(key, item, -decrNum);
    }

    /**
     * 根据key获取set的所有项值
     * @param key 键名
     * @return
     */
    public static Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断set中是否存在value
     * @param key 键名
     * @param value 值
     * @return boolean 存在返回true，否则返回false
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将value存放入set
     * @param key 键名
     * @param values 一个或多个value
     * @return long 成功插入的个数
     */
    public static long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将value存放入set（带存活时间）
     * @param key 键名
     * @param time 存活时间
     * @param values 一个或多个value
     * @return long 成功插入的个数
     */
    public static long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set的长度
     * @param key 键名
     * @return long set长度
     */
    public static long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除set的value
     * @param key 键名
     * @param values 一个或多个值
     * @return long 成功删除的个数
     */
    public static long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据key获取list
     * @param key 键名
     * @param start 开始索引
     * @param end 结束索引 0到-1指所有
     * @return List<Object>
     */
    public static List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过索引获取list值
     * @param key 键名
     * @param index 索引
     * @return Object 值
     */
    public static Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取list的长度
     * @param key 键名
     * @return list的长度
     */
    public static long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 向list插入值
     * @param key 键名
     * @param value 值
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向list插入值（带存活时间）
     * @param key 键名
     * @param value 值
     * @param time 存活时间
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入list
     * @param key 键名
     * @param value list
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 插入list
     * @param key 键名
     * @param value list
     * @param time 存活时间
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过索引更新list的某个值
     * @param key 键名
     * @param index 索引
     * @param value 值
     * @return boolean 成功返回true，否则返回false
     */
    public static boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除list指定数量个的值
     * @param key 键名
     * @param count 指定数量
     * @param value 值
     * @return 成功删除的个数
     */
    public static long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
