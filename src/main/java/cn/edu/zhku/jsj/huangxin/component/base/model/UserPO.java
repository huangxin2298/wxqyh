package cn.edu.zhku.jsj.huangxin.component.base.model;

import java.util.Date;

public class UserPO implements IBasePO {
    private String id;
    private String userName;
    private String password;
    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String _getTableName() {
        return "tb_user_info";
    }

    @Override
    public String _getPKName() {
        return "id";
    }

    @Override
    public Object _getPKValue() {
        return String.valueOf(this.id);
    }

    @Override
    public void _setPKValue(Object id) {
        this.id = (java.lang.String)id;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
