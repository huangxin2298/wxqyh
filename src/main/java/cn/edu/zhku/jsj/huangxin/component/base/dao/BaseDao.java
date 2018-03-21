package cn.edu.zhku.jsj.huangxin.component.base.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseDao {
    /**
     * 插入实体
     * @param tableName 实体对应的表名
     * @param fieldMap key：字段名 value：字段值
     */
    void insertPO(@Param("tableName") String tableName,@Param("fieldMap") Map<String,Object> fieldMap);
    /**
     * 批量插入实体
     * @param tableName 实体对应的表名
     * @param fieldMapList 字段map列表
     */
    void batchInsertPO(@Param("tableName") String tableName,@Param("fieldMapList") List<Map<String ,Object>>
            fieldMapList);

    /**
     * 更新实体
     * @param tableName 实体对应的表名
     * @param fieldMap 更新字段 key：字段名 value：字段值
     * @param paramMap 条件字段 key：字段名 value：字段值
     *
     */
    void updatePO(@Param("tableName")String tableName,@Param("fieldMap") Map<String,Object> fieldMap,@Param("paramMap")
            Map<String,
                  Object> paramMap);
    /**
     * 删除实体
     * @param tableName 实体对应的表名
     * @param paramMap 条件字段 key：字段名 value：字段值
     */
    void deletePO(@Param("tableName")String tableName,@Param("paramMap") Map<String, Object> paramMap);
    /**
     * 批量删除实体
     * @param tableName 实体对应的表名
     * @param paramMap 条件字段 key：字段名 value：字段值数组
     */
    void batchDeletePO(@Param("tableName")String tableName,@Param("paramMap") Map<String, Object[]> paramMap);
    /**
     * 通过主键查询实体
     * @param tableName 实体对应的表名
     * @param paramMap 条件字段 key：字段名 value：字段值
     */
    Map<String, Object> searchPOByPk(@Param("tableName")String tableName,@Param("paramMap") Map<String, Object>
            paramMap);
}
