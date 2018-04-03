package cn.edu.zhku.jsj.huangxin.component.base.service.impl;

import cn.edu.zhku.jsj.huangxin.component.base.dao.BaseDao;
import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;
import cn.edu.zhku.jsj.huangxin.component.base.service.IBaseService;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService implements IBaseService{
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }
    @Autowired
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
    public void insertPO(IBasePO po){
        if(po != null){
            Map<String, Object> fieldMap = BaseUtils.getFieldMap(po);
            if(fieldMap != null && fieldMap.size() > 0){
                baseDao.insertPO(po._getTableName(),fieldMap);
            }
        }
    }

    @Override
    public void batchInsertPO(List<? extends IBasePO> poList) {
        if(poList != null && poList.size() > 0){
            List<Map<String ,Object>> filedMapList = BaseUtils.getFieldMapList(poList);
            baseDao.batchInsertPO(poList.get(0)._getTableName(),filedMapList);
        }
    }

    @Override
    public void updatePO(IBasePO po) {
        if(po != null){
            Map<String, Object> fieldMap = BaseUtils.getFieldMap(po);
            if(fieldMap != null && fieldMap.size() > 0){
                fieldMap.remove(po._getPKName());//去掉主键
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put(po._getPKName(),po._getPKValue());
                baseDao.updatePO(po._getTableName(),fieldMap,paramMap);
            }
        }
    }

    @Override
    public void deletePO(Class<? extends IBasePO> clazz,Object pkValue) {
        if(!AssertUtils.isEmpty(pkValue)){
            try {
                Map<String, Object> paramMap = new HashMap<>();
                IBasePO basePO = clazz.newInstance();
                paramMap.put(basePO._getPKName(),pkValue);
                baseDao.deletePO(basePO._getTableName(),paramMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void batchDeletePO(Class<? extends IBasePO> clazz, Object[] pkValues) {
        if(pkValues != null && pkValues.length > 0){
            try {
                Map<String, Object[]> paramMap = new HashMap<>();
                IBasePO basePO = clazz.newInstance();
                paramMap.put(basePO._getPKName(),pkValues);
                baseDao.batchDeletePO(basePO._getTableName(),paramMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public<T extends IBasePO> T searchPOByPk(Class<T> clazz, Object pkValue) {
        T basePO = null;
        if(!AssertUtils.isEmpty(pkValue)){
            try {
                Map<String, Object> paramMap = new HashMap<>();
                basePO = clazz.newInstance();
                paramMap.put(basePO._getPKName(),pkValue);
                Map<String, Object> fieldMap = baseDao.searchPOByPk(basePO._getTableName(),paramMap);
                if(!AssertUtils.isEmpty(fieldMap)){
                    basePO = BaseUtils.mapToPO(fieldMap,clazz);
                }
                System.out.println(fieldMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return basePO;
    }

}
