package cn.edu.zhku.jsj.huangxin.component.base.service;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;

import java.util.List;

public interface IBaseService {
    /**
     * 插入实体
     * @param po 实体
     */
    void insertPO(IBasePO po);

    /**
     * 批量插入实体
     * @param poList 实体列表
     */
    void batchInsertPO(List<? extends IBasePO> poList);

    /**
     * 更新实体
     * @param po 实体
     */
    void updatePO(IBasePO po);
    /**
     * 删除实体
     * @param clazz 实体类型
     * @param pkValue 实体主键值
     */
    void deletePO(Class<? extends IBasePO> clazz,Object pkValue);
    /**
     * 删除实体
     * @param clazz 实体类型
     * @param pkValues 实体主键值数组
     */
    void batchDeletePO(Class<? extends IBasePO> clazz,Object[] pkValues);
    /**
     * 通过主键查询实体
     * @param clazz 实体类型
     * @param pkValue 实体主键值
     */
    public<T extends IBasePO> T searchPOByPk(Class<T> clazz, Object pkValue);
}
