package com.bridge.common.utils.http;

import com.bridge.common.config.Global;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClientUtil 工具类
 *
 * @author bridge
 */
public class HttpClientUtil {

    //E9服务IP
    private static final String ip;
    //E9服务端口号
    private static final String port;
    //超时时间
    private static final int readTimedOut;

    static {
        ip = Global.getE9Ip();
        port = Global.getE9Port();
        readTimedOut = Integer.valueOf(Global.getE9TimeOut());
    }

    private static String getBaseUrl() {
        return "http://" + ip + ":" + port + "/";
    }

    /**
     * 调用demo
     */
    public static void main(String[] args) {
        //String ip = Global.getE9Ip();
        //String port = Global.getE9Port();
        String url = "slmME/rs/ExceptionRocordInfoResource/findExceptionRocord";
        /**json  转码   打开就能用*/
        /*String json2 = "{\"mouldNum\":'7184-8708',\"productdrawing\":'7184-8708',\"facilityCode\":'ZS-1',\"exceptionType\":'待机',\"bak\":'BZ0007',\"sbUserCode\":'BZ0001',\"startTime\":'2018-08-16 08:30',\"endTime\":'2018-08-16 09:30'}";

        try {
            json2 = URLEncoder.encode(json2,"utf-8");
        } catch (UnsupportedEncodingException e) {

        }*/
        //String responseContent = HttpClientUtil.sendHttpPost(ip,port,url);
        //System.out.println("返回值:" + responseContent);
    }

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimedOut).setConnectTimeout(readTimedOut)
            .setConnectionRequestTimeout(readTimedOut).build();

    private static HttpClientUtil instance = null;

    private HttpClientUtil() {
    }

    /**
     * 发送 post请求
     * String ip, String port,
     *
     * @param
     */
    public static String sendHttpPost(String url) {
        String httpUrl = getBaseUrl() + url;
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private static String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }


    /**
     * 发送 post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendHttpPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(getBaseUrl()+url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 发送 get请求
     *
     * @param
     */
    public static String sendHttpGet(String url) {
        String httpUrl = getBaseUrl();
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }


    /**
     * 发送Get请求
     *
     * @param
     * @return
     */
    private static String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }
}
