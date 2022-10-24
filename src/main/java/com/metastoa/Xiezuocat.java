package com.metastoa;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Xiezuocat {

    private String checkUrl = "https://apicheck.xiezuocat.com/api/text_check";
    private String rewriteUrl = "https://api.xiezuocat.com/para_api_v2";
    private String loginUrl = "https://xiezuocat.com/api/open/login";
    private String secretKey;

    public Xiezuocat() {
    }

    public Xiezuocat(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public String getRewriteUrl() {
        return rewriteUrl;
    }

    public void setRewriteUrl(String rewriteUrl) {
        this.rewriteUrl = rewriteUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String check(String postData) {
        return doPost(this.checkUrl, postData);
    }

    public String rewrite(String postData) {
        return doPost(this.rewriteUrl, postData);
    }

    public String signature(String appId, String secretKey, String id) {
        String timestamp=String.valueOf(new Date().getTime());//时间戳
        Map<String,Object> paraMap =new HashMap<>();
        paraMap.put("appId", appId);
        paraMap.put("uid", id);
        paraMap.put("timestamp", timestamp);
        //签名字符串
        String sign = SM3SignatureUtil.signatureSM3(paraMap,secretKey);
        paraMap.put("sign", sign);
        try {
            String base64EncodedString = Base64.getEncoder().encodeToString(JSON.toJSON(paraMap).toString().getBytes("utf-8"));
            return base64EncodedString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String doPost(String url, String data) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("secret-key", secretKey)
                .addHeader("Cookie", "JSESSIONID=0119E4899982EC4BA8BD173217399EF4")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
