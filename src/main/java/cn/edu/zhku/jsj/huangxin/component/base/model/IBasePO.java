package cn.edu.zhku.jsj.huangxin.component.base.model;

public interface IBasePO {
    String _getTableName();
    String _getPKName();
    Object _getPKValue();
    void _setPKValue(Object id);
}
