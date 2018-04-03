package cn.edu.zhku.jsj.huangxin.component.base.util;

import cn.edu.zhku.jsj.huangxin.component.admin.model.TbAdminConfig;
import cn.edu.zhku.jsj.huangxin.component.admin.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigUtil {

	private final static transient Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	private static IAdminService adminService = SpringContextHolder.getBean(IAdminService.class);

	private final static Map<String, String> configMap = new HashMap<>();

	static{
		logger.error("------开始加载配置项------");
		Map<String, Object> searchMap = new HashMap<>();
		List<TbAdminConfig> configList = adminService.getConfigList(searchMap);
		for(TbAdminConfig adminConfig : configList){
			configMap.put(adminConfig.getConfigName(),adminConfig.getConfigValue());
		}
	}

	public static String getConfigValue(String configName){
		return configMap.get(configName);
	}

	public static void flashConfig(){
		logger.error("------正在刷新配置项------");
		Map<String, Object> searchMap = new HashMap<>();
		List<TbAdminConfig> configList = adminService.getConfigList(searchMap);
		configMap.clear();
		for(TbAdminConfig adminConfig : configList){
			configMap.put(adminConfig.getConfigName(),adminConfig.getConfigValue());
		}
	}
}
