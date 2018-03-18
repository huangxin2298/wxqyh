package cn.edu.zhku.jsj.huangxin.component.base.filter;

import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizeFilter implements Filter {

    private static Logger logger = Logger.getLogger(AuthorizeFilter.class);
    private String codeGetUrl = "https://open.weixin.qq" +
            ".com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    private String corpId = "ww76a306ced47102bd";
    private String baseUrl = "http://threekingstar.xicp.io/wxqyh";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String reqUrl = baseUrl+req.getRequestURI();
        String code = req.getParameter("code");
        logger.error(code);
        if(AssertUtils.isEmpty(code)){
            String redirectUrl = java.net.URLEncoder.encode(reqUrl,"utf-8");
            String codeGetTempUrl = codeGetUrl.replace("CORPID",corpId).replace("REDIRECT_URI",redirectUrl).replace
                    ("SCOPE","snsapi_base").replace("STATE","123");
            resp.sendRedirect(codeGetTempUrl);
            return;
        }else{
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
