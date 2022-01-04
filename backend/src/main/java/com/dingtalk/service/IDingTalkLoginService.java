package com.dingtalk.service;

import com.dingtalk.vo.DingUserVO;

import java.util.Map;


public interface IDingTalkLoginService {
    Map<String, Object> addUser(DingUserVO dingUserVO);

}
