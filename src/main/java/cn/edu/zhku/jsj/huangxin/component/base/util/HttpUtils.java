package cn.edu.zhku.jsj.huangxin.component.base.util;

import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static  RequestConfig requestConfig = SpringContextHolder.getBean("requestConfig");
    private static CloseableHttpClient httpClient = SpringContextHolder.getBean(CloseableHttpClient.class);

    /**
     * get请求
     * @param url 请求地址
     * @return String
     */
    public static HttpResult doGet(String url){
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);//设置请求参数
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            return new HttpResult(response.getStatusLine().getStatusCode(),content);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 带参数的get请求
     * @param url 请求地址
     * @param params 请求参数
     * @return HttpResult
     */
    public static HttpResult doGet(String url , Map<String, String> params){
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
            if(params != null){
                for(String key : params.keySet()){
                    uriBuilder.setParameter(key, params.get(key));
                }
            }
            return doGet(uriBuilder.build().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        }
    }

    public static HttpResult doPost(String url , Map<String, String> params){
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {

            if(params != null){
                // 设置2个post参数，一个是scope、一个是q
                List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
                for(String key : params.keySet()){
                    parameters.add(new BasicNameValuePair(key, params.get(key)));
                }
                // 构造一个form表单式的实体
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
                // 将请求实体设置到httpPost对象中
                httpPost.setEntity(formEntity);
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static HttpResult doPostJson(String url , String json){
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            if(!AssertUtils.isEmpty(json)){
                //标识出传递的参数是 application/json
                StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(stringEntity);
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResult(999,e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
