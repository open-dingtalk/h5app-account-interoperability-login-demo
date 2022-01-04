package com.dingtalk.utils;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.config.AppConfig;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DingInfoMethod {
    private static final Logger logger = LoggerFactory.getLogger(DingInfoMethod.class);
    /**
     * 钉钉接口地址
     */
    public static final String DING_HOST = "https://oapi.dingtalk.com";
    /**
     * 获取三方企业应用开发获取access_token的URL
     */
    public static final String GET_SERVICE_ACCESS_TOKEN_URL = DING_HOST + "/gettoken";


    /**
     * 企业内部应用，获取企业token
     *
     * @return
     */
    public String getAccessToken() {
        logger.debug("获取token开始");
        String token = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_SERVICE_ACCESS_TOKEN_URL);
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(AppConfig.getAppKey());
            request.setAppsecret(AppConfig.getAppSecret());
            request.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(request);
            String body = rsp.getBody();
            System.out.println(body);
            JSONObject jsonObject = JSONObject.parseObject(body);
            token = jsonObject.getString("access_token");
            System.out.println(token);
            return token;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return token;
    }

}
