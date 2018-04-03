package cn.edu.zhku.jsj.huangxin.component.base.filter;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbUserInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.util.AddressbookUtil;
import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.ConfigUtil;
import cn.edu.zhku.jsj.huangxin.component.base.util.WeiXinUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizeFilter implements Filter {

    private static Logger logger = Logger.getLogger(AuthorizeFilter.class);
    private String codeGetUrl = "https://open.weixin.qq" +
            ".com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    private static String corpId = ConfigUtil.getConfigValue("corp_id");
    private static String baseUrl = ConfigUtil.getConfigValue("web_port");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String reqUrl = baseUrl+req.getRequestURI();
        String code = req.getParameter("code");
		String agentCode = req.getParameter("agentCode");
		if(AssertUtils.isEmpty(agentCode)){
			agentCode = req.getParameter("state");
		}
        if(AssertUtils.isEmpty(code)){
            String redirectUrl = java.net.URLEncoder.encode(reqUrl,"utf-8");
            String codeGetTempUrl = codeGetUrl.replace("CORPID",corpId).replace("REDIRECT_URI",redirectUrl).replace
                    ("SCOPE","snsapi_base").replace("STATE",agentCode);
            resp.sendRedirect(codeGetTempUrl);
            return;
        }else{
            HttpSession session = req.getSession();
            TbUserInfoPO userInfo = (TbUserInfoPO) session.getAttribute("userId");
            if(AssertUtils.isEmpty(userInfo)){
				HttpResult httpResult = WeiXinUtils.getUserinfo(WeiXinUtils.getAccessToken(agentCode, AddressbookUtil.getAdminOrgId()),code);
				logger.error("-------author:"+httpResult.toString());
				JSONObject jsonObject = JSONObject.parseObject(httpResult.getContent());
				if(httpResult.getCode()!=200){
					logger.error("-------微信授权失败！------");
				}else{
					if(!"0".equals(jsonObject.getString("errcode"))){
						logger.error("------根据code获取成员信息失败!错误码为"+jsonObject.getString("errcode")+"------");
					}else{
						String userId = jsonObject.getString("UserId");
						if(!AssertUtils.isEmpty(userId)){
							logger.error("------根据code获取成员信息成功！userId为："+userId+" ------");
							session.setAttribute("userId",userId);
						}
					}
				}
			}
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
