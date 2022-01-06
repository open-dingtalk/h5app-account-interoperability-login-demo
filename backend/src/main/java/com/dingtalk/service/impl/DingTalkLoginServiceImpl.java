package com.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.service.IDingTalkLoginService;
import com.dingtalk.utils.DingInfoMethod;
import com.dingtalk.vo.DingUserVO;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DingTalkLoginServiceImpl implements IDingTalkLoginService {

    @Autowired
    private DingInfoMethod dingInfoMethod;

    /**
     * map模拟数据库存储
     */
    private static Map<String, JSONObject> userMap = new HashMap<>();

    static {
        userMap.put("user", null);
    }

    private Logger log = LoggerFactory.getLogger(DingTalkLoginServiceImpl.class);

    public Map<String, Object> login(String requestAuthCode) {
        // 获取access_token，注意正式代码要有异常流处理
        Map<String, Object> returnMap = new HashMap<>();
        String accessToken = dingInfoMethod.getAccessToken();
        // 获取用户信息
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            returnMap.put("code", -1);
            return returnMap;
        }
        if (null == response) {
            returnMap.put("code", -1);
            return returnMap;
        }
        // 查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();
        String body = response.getBody();
        Map json = (Map) JSON.parse(body);
        String name = json.get("name") == null ? "" : json.get("name").toString();
        //map模拟数据库读取用户
        JSONObject jsonObject = userMap.get("user");
        if (jsonObject == null) {
            returnMap.put("code", 2);
            returnMap.put("dingUserId", userId);
            returnMap.put("name", name);
            return returnMap;
        }
        if (jsonObject != null) {
            returnMap.put("data", jsonObject);
            returnMap.put("code", 1);
            return returnMap;
        }
        // 没绑定过就跳转到绑定页
        returnMap.put("code", 2);
        returnMap.put("dingUserId", userId);
        returnMap.put("name", name);
        return returnMap;
    }


    /**
     * 添加用户
     * demo为求简便，该步骤用文件读取代替数据入库操作，实际使用中请自行将数据入库
     *
     * @param dingUserVO
     * @return
     */
    @Override
    public Map<String, Object> addUser(DingUserVO dingUserVO) {
        Map<String, Object> returnMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", dingUserVO.getUserName());
        jsonObject.put("dingUserName", dingUserVO.getDingUserName());
        jsonObject.put("dingUserId", dingUserVO.getDingUserId());
        jsonObject.put("password", dingUserVO.getPassword());
        userMap.put("user", jsonObject);
        if (userMap.get("user") != null) {
            returnMap.put("code", 1);
            returnMap.put("msg", "绑定成功！");
            returnMap.put("dingUserName", dingUserVO.getDingUserName());
            returnMap.put("userName", dingUserVO.getUserName());
        } else {
            returnMap.put("code", -1);
            returnMap.put("msg", "绑定失败！");
        }
        return returnMap;
    }

}
