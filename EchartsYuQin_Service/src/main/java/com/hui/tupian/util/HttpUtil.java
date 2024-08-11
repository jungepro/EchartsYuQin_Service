package com.hui.tupian.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 爬虫类
 */
public class HttpUtil {
    /**
     * HttpClient send Post
     */
    public byte[] httpClientPost(final String url, final List<NameValuePair> params) {
        // 客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // post实例
        HttpPost httpPost = new HttpPost(url);
        // 返回请求
        CloseableHttpResponse response = null;
        // IO流
        InputStream inputStream = null;
        try {
            // 参数设置
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            entity.setContentType("application/json;charset=utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            // 返回请求
            response = httpClient.execute(httpPost);
            // 获取响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                System.out.println("响应编码:" + response.getStatusLine().getStatusCode());
                System.out.println("请求失败:" + response.getStatusLine().getReasonPhrase());
            } else {
                System.out.println("响应编码:" + response.getStatusLine().getStatusCode());
                System.out.println("请求成功:" + response.getStatusLine().getReasonPhrase());
                // 获取全部的请求头
                Header[] headers = response.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println("全部的请求头：" + headers[i]);
                }
                // 获取响应消息实体
                HttpEntity httpEntity = response.getEntity();
                // 返回IO流
                inputStream = httpEntity.getContent();
                // 返回bytes
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                // 返回响应内容
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭inputStream和httpResponse
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * HttpClient send Get
     */
    public byte[] httpClientGet(final String url) {
        // 客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // post实例
        HttpGet httpGet = new HttpGet(url);
        // 返回请求
        CloseableHttpResponse response = null;
        // IO流
        InputStream inputStream = null;
        try {
            // 参数设置
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            // 返回请求
            response = httpClient.execute(httpGet);
            // 获取响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                System.out.println("响应编码:" + response.getStatusLine().getStatusCode());
                System.out.println("请求失败:" + response.getStatusLine().getReasonPhrase());
            } else {
                System.out.println("响应编码:" + response.getStatusLine().getStatusCode());
                System.out.println("请求成功:" + response.getStatusLine().getReasonPhrase());
                // 获取全部的请求头
                Header[] headers = response.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println(headers[i]);
                }
                // 获取响应消息实体
                HttpEntity httpEntity = response.getEntity();
                // 返回IO流
                inputStream = httpEntity.getContent();
                // 返回bytes
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                // 返回响应内容
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭inputStream和httpResponse
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * HttpURLConnection send Post
     */
    public byte[] httpPost(final String uri, final String params) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            // 读取超时
            connection.setReadTimeout(5000);
            // 连接超时
            connection.setConnectTimeout(5000);
            // 缓存
            connection.setUseCaches(false);
            // 发送方式
            connection.setRequestMethod("POST");
            // 允许输入输出
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置headers
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
            // 参数设置
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(params.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            // 返回码
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("请求失败:" + connection.getResponseMessage());
                System.out.println("响应编码:" + connection.getResponseCode());
                return null;
            } else {
                System.out.println("请求成功:" + connection.getResponseMessage());
                System.out.println("响应编码:" + connection.getResponseCode());
                // 返回headers
                Map<String, List<String>> map = connection.getHeaderFields();
                System.out.println(map.toString());
                // 返回流
                inputStream = connection.getInputStream();
                // 返回bytes
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                // 返回响应内容
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭inputStream和httpResponse
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * HttpURlConnection send Get
     */
    public byte[] httpGet(final String uri) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            // 读取超时
            connection.setReadTimeout(5000);
            // 连接超时
            connection.setConnectTimeout(5000);
            // 缓存
            connection.setUseCaches(false);
            // 发送方式
            connection.setRequestMethod("GET");
            // 设置headers
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
            // 返回码
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("请求失败:" + connection.getResponseMessage());
                System.out.println("响应编码:" + connection.getResponseCode());
                return null;
            } else {
                System.out.println("请求成功:" + connection.getResponseMessage());
                System.out.println("响应编码:" + connection.getResponseCode());
                // 返回headers
                Map<String, List<String>> map = connection.getHeaderFields();
                System.out.println(map.toString());
                // 返回流
                inputStream = connection.getInputStream();
                // 返回bytes
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                // 返回响应内容
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭inputStream和httpResponse
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "http://img1.juimg.com/181219/330479-1Q2191H04958.jpg";
        //byte[] bytes = new HttpUtil().httpClientGet(url);
        byte[] bytes = new HttpUtil().httpGet(url);
        System.out.println(bytes.length);
    }
}
