package com.liang.demo.snowwalking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by lianghaofei on 2018/1/8.
 */
@RestController
@RequestMapping("/httptest")
public class HttpTestController {
    private static HashMap<String, String> mData = new HashMap<String, String>();
    private final static String HTTP_URL = "http://localhost:24235/p2/httptest";

    @RequestMapping("testget")
    public String testGet(String host) {
        mData.put("name", "HongBin");
        mData.put("sex", "male");
        return "GetResult:" + startGet("http://" + host + ":24235/p2/httptest");
    }


    public String startGet(String path) {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            //GET请求直接在链接后面拼上请求参数
            String mPath = path + "?";
            for (String key : mData.keySet()) {
                mPath += key + "=" + mData.get(key) + "&";
            }
            mPath = mPath.substring(0, mPath.length() - 1);
            URL url = new URL(mPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //Get请求不需要DoOutPut
            conn.setDoOutput(false);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //连接服务器
            conn.connect();
            // 取得输入流，并使用Reader读取
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
