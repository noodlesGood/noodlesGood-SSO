package com.noodlesGuo.ssoClient.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
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
 * http请求相关工具类
 */
public class HttpUtil {

    /**
     * 发送post请求
     * @param url
     * @param paramMap
     * @param headerMap
     * @return String
     */
    public static String post(String url, Map<String,String> paramMap,Map<String,String> headerMap){
        CloseableHttpClient httpclient = null;
        String resultStr = null;
        HttpPost httppost = new HttpPost(url);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (paramMap!=null){
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {//设置请求体
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(formparams), Consts.UTF_8);
            httppost.setEntity(stringEntity);
        }

        if (headerMap!=null){
            for (Map.Entry<String,String> entry:headerMap.entrySet()) {//设置头信息
                httppost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httppost);
            resultStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            httppost.releaseConnection();
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httppost!=null){
                httppost.abort();
            }
            if (httpclient!=null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr;
    }


    /**
     * 封装get请求
     * @param url
     * @param headerMap
     * @return
     */
    public static String get(String url,Map<String,String> headerMap){
        CloseableHttpClient httpclient = null;
        String resultStr = null;
        HttpGet httpGet = new HttpGet(url);
        if (headerMap!=null){
            for (Map.Entry<String,String> entry:headerMap.entrySet()) {//设置头信息
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }

        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpGet);
            resultStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpclient!=null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr;
    }
}
