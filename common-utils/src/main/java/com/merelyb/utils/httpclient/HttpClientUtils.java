package com.merelyb.utils.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.httpclient
 * @作者: LiM
 * @创建时间: 2018-07-27 10:55
 * @Description: HttpClient 工具封装类
 */
public class HttpClientUtils {

    private PoolingHttpClientConnectionManager cm;
    private  String UTF_8 = "UTF-8";
    private RequestConfig requestConfig = null;

    public HttpClientUtils() {
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        init();
    }

    public HttpClientUtils(int connectTimeOut, int connectRequestTimeOut, int socketTimeOut) {
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeOut).setConnectionRequestTimeout(connectRequestTimeOut)
                .setSocketTimeout(socketTimeOut).build();
        init();
    }

    private void init(){
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private CloseableHttpClient getHttpClient() {

        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * GET请求
     *
     * @param url
     * @param sEncode
     * @return
     */
    public HttpClientResult httpGetRequest(String url, String sEncode) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return getResult(httpGet, sEncode);
    }

    /**
     * GET请求
     *
     * @param url
     * @return
     */
    public HttpClientResult httpGetRequest(String url) throws Exception {
        return httpGetRequest(url, UTF_8);
    }

    /**
     * GET请求
     *
     * @param url
     * @param params
     * @param sEncode
     * @return
     * @throws Exception
     */
    public HttpClientResult httpGetRequest(String url, Map<String, Object> params, String sEncode) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        httpGet.setConfig(requestConfig);
        return getResult(httpGet, sEncode);
    }

    /**
     * GET请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public HttpClientResult httpGetRequest(String url, Map<String, Object> params) throws Exception {
        return httpGetRequest(url, params, UTF_8);
    }

    /**
     * GET请求
     *
     * @param url
     * @param headers
     * @param params
     * @param sEncode
     * @return
     * @throws Exception
     */
    public HttpClientResult httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params, String sEncode)
            throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        httpGet.setConfig(requestConfig);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet, sEncode);
    }

    /**
     * GET请求
     *
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws Exception
     */
    public HttpClientResult httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws Exception {
        return httpGetRequest(url, headers, params, UTF_8);
    }

    /**
     * POST请求
     *
     * @param url
     * @param sEncode
     * @return
     */
    public HttpClientResult httpPostRequest(String url, String sEncode) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        return getResult(httpPost, sEncode);
    }


    /**
     * POST请求
     *
     * @param url
     * @return
     */
    public HttpClientResult httpPostRequest(String url) throws Exception {
        return httpPostRequest(url, UTF_8);
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param sEncode
     * @return
     * @throws Exception
     */
    public HttpClientResult httpPostRequest(String url, Map<String, Object> params, String sEncode) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost, sEncode);
    }

    /**
     * POST请求
     * json 字符串放到body里面
     *
     * @param url     请求的url
     * @param params  json字符串
     * @param sEncode 编码
     * @return
     * @throws Exception
     */
    public HttpClientResult sendPostRequest(String url, String params, String sEncode) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new StringEntity(params, UTF_8));
        return getResult(httpPost, sEncode);
    }

    /**
     * POST请求
     * json 字符串放到body里面
     *
     * @param url    请求的url
     * @param params json字符串
     * @return
     * @throws Exception
     */
    public HttpClientResult sendPostRequest(String url, String params) throws Exception {
        return sendPostRequest(url, params, UTF_8);
    }


    /**
     * POST请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public HttpClientResult httpPostRequest(String url, Map<String, Object> params) throws Exception {
        return httpPostRequest(url, params, UTF_8);
    }

    /**
     * POST请求
     *
     * @param url
     * @param headers
     * @param params
     * @param sEncode
     * @return
     * @throws Exception
     */
    public HttpClientResult httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params, String sEncode)
            throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            jsonObject.put(param.getKey(), String.valueOf(param.getValue()));
        }

        StringEntity entity = new StringEntity(jsonObject.toString(), UTF_8);
        entity.setContentEncoding(UTF_8);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        return getResult(httpPost, sEncode);
    }

    /**
     * POST请求
     *
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws Exception
     */
    public HttpClientResult httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws Exception {
        return httpPostRequest(url, headers, params, UTF_8);
    }

    /**
     * 跨域
     *
     * @param params
     * @return
     */
    private  ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private HttpClientResult getResult(HttpRequestBase request, String sEncode) throws Exception {
        HttpClientResult httpClientResult = new HttpClientResult();
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = getHttpClient();

            response = httpClient.execute(request);
            int iStatusCode = response.getStatusLine().getStatusCode();
            switch (iStatusCode) {
                case HttpStatus.SC_OK: {
                    //正常加载 解析数据
                    if (response != null) {
                        HttpEntity entity = response.getEntity();
                        httpClientResult.setCode(HttpStatus.SC_OK);
                        httpClientResult.setSuccessed(true);
                        if (entity != null) {
                            // long len = entity.getContentLength();// -1 表示长度未知
                            httpClientResult.setData(EntityUtils.toString(entity, sEncode));

                        }
                    }
                    break;
                }
                case HttpStatus.SC_BAD_REQUEST: {
                    httpClientResult.setCode(HttpStatus.SC_BAD_REQUEST);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("请求无效");
                    break;
                }
                case HttpStatus.SC_FORBIDDEN: {
                    httpClientResult.setCode(HttpStatus.SC_FORBIDDEN);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("禁止访问");
                    break;
                }

                case HttpStatus.SC_UNAUTHORIZED: {
                    httpClientResult.setCode(HttpStatus.SC_UNAUTHORIZED);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("未经授权的请求");
                    break;
                }
                case HttpStatus.SC_INTERNAL_SERVER_ERROR: {
                    httpClientResult.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("内部服务器错误");
                    break;
                }

                case HttpStatus.SC_GONE: {
                    httpClientResult.setCode(HttpStatus.SC_GONE);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("无法找到文件");
                    break;
                }
                case HttpStatus.SC_BAD_GATEWAY: {
                    httpClientResult.setCode(HttpStatus.SC_BAD_GATEWAY);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("网关错误");
                    break;
                }
                case HttpStatus.SC_GATEWAY_TIMEOUT: {
                    httpClientResult.setCode(HttpStatus.SC_GATEWAY_TIMEOUT);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("连接超时");
                    break;
                }
                default: {
                    httpClientResult.setCode(iStatusCode);
                    httpClientResult.setSuccessed(false);
                    httpClientResult.setMsg("未知错误");
                    break;
                }
            }

        } catch (Exception e) {

            throw e;

        } finally {
            if (response != null) {
                response.close();
            }
            request.abort();

        }

        return httpClientResult;
    }
}
