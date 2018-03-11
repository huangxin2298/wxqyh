package cn.edu.zhku.jsj.huangxin.component.base;
import cn.edu.zhku.jsj.huangxin.component.base.model.UserPO;
import cn.edu.zhku.jsj.huangxin.component.base.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service" +
        ".xml","classpath:spring/applicationContext-transaction.xml"})
public class BaseServiceTest{
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }
    @Resource(name="userService")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Test
    public void insertTest(){
        UserPO userPO = new UserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("huangxin");
        userService.insertPO(userPO);
        System.out.println(userPO.getId());
    }
    @Test
    public void batchInsertTest(){
        UserPO userPO = new UserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("huangxin2");
        userPO.setPassword("1232");
        UserPO userPO2 = new UserPO();
        userPO2.setId(UUID.randomUUID().toString());
        userPO2.setUserName("huangxin3");
        userPO2.setPassword("1233");
        List<UserPO> userList = new ArrayList<>();
        userList.add(userPO);
        userList.add(userPO2);
        userService.batchInsertPO(userList);
    }
    @Test
    public void updateTest(){
        UserPO userPO = new UserPO();
        userPO.setId("4a781eed-43f5-4587-8dec-34c349b00732");
        userPO.setUserName("update");
        userPO.setPassword("updatepw");
        userService.updatePO(userPO);
    }
    @Test
    public void deleteTest(){
        userService.deletePO(UserPO.class,"59434cb5-bee5-4fd2-addb-c74ca33d8ad2");
    }
    @Test
    public void batchDeleteTest(){
        Object[] pkValues = {"30669482-7096-4676-83a2-6258960d8bd7","a6ef11ad-240b-4dd5-8fee-a8c1c59d717c"};
        userService.batchDeletePO(UserPO.class,pkValues);
    }
}
