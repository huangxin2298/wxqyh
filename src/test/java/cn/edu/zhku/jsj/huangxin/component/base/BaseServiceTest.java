package cn.edu.zhku.jsj.huangxin.component.base;
import cn.edu.zhku.jsj.huangxin.component.base.model.UserPO;
import cn.edu.zhku.jsj.huangxin.component.base.service.IUserService;
import cn.edu.zhku.jsj.huangxin.component.base.util.HttpUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.RedisUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.WeiXinUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service" +
        ".xml","classpath:spring/applicationContext-transaction.xml","classpath:spring/applicationContext-redis.xml",
        "classpath:spring/applicationContext-httpclient.xml"})
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
        userPO.setBirthday(new Date());
        userService.insertPO(userPO);
        System.out.println(userPO.getId());
    }
    @Test
    public void batchInsertTest(){
        UserPO userPO = new UserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("huangxin2");
        userPO.setPassword("1232");
        userPO.setBirthday(new Date());
        UserPO userPO2 = new UserPO();
        userPO2.setId(UUID.randomUUID().toString());
        userPO2.setUserName("huangxin3");
        userPO2.setPassword("1233");
        userPO2.setBirthday(new Date());
        userPO2.setBirthday(new Date());
        List<UserPO> userList = new ArrayList<>();
        userList.add(userPO);
        userList.add(userPO2);
        userService.batchInsertPO(userList);
    }
    @Test
    public void updateTest(){
        UserPO userPO = new UserPO();
        userPO.setId("21111555-e7aa-452c-a7b0-1eacc3a1dcee");
        userPO.setUserName("update");
        userPO.setPassword("updatepw");
        userPO.setBirthday(new Date());
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
    @Test
    public void searchPOByPKTest(){
        UserPO userPO = userService.searchPOByPk(UserPO.class,"4a781eed-43f5-4587-8dec-34c349b00732");
        System.out.println(userPO);
    }
    @Test
    public void redisTest(){
        RedisUtils.set("userName","HuangXin",7000);
        System.out.println("_______________"+RedisUtils.hasKey("userName"));
        System.out.println(RedisUtils.get("userName"));
    }
    @Test
    public void httpUtilTest(){
        /*Map<String,String> map =new HashMap<>();
        map.put("corpid","ww76a306ced47102bd");
        map.put("corpsecret","cyuPpko1kkMSXSZaIalvvWqww-28aat7DhckTW2pP_s");
        System.out.println(HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken",map));*/
        Map<String,String> map =new HashMap<>();
        map.put("access_token","uARO4qjL-zkGlif2J1OPp4KxT7xOWe4jZ5ftRmg-RwNx_Dfi_KAmCR2WoVJG40N6gND-jA8CusZ7ClEDBY38oPXOwtJNDXmOoSJfa4crL6FNlQlUM3xxk-sE94mlepEAY3-YlqXY4iUD9kiOOQTugftSpHVa7Uo_etow8WmsGbh-1MNc0Ozc9TlG-QapNuuw7-5HEKbQxoiTafbhcviEEA");
        map.put("agentid","1000004");
        System.out.println(HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/agent/get",map));
    }
    @Test
    public void weiXinUtilTest(){
        String accessCode = WeiXinUtils.getAccessToken("org","1");
        System.out.println("------------\n"+accessCode);
    }
}
