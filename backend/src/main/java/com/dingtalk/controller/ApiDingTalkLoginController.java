package com.dingtalk.controller;

import com.dingtalk.config.AppConfig;
import com.dingtalk.service.IDingTalkLoginService;
import com.dingtalk.service.impl.DingTalkLoginServiceImpl;
import com.dingtalk.vo.DingUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 企业内部应用免登，欢迎业务咨询
 *
 * @author renzhun
 * @mobile
 */
@RestController
@RequestMapping("/api/demo")
public class ApiDingTalkLoginController {

    @Autowired
    private DingTalkLoginServiceImpl dingTalkLoginService;

    @Autowired
    private IDingTalkLoginService iDingTalkLoginService;

    /**
     * 欢迎页面，通过 /welcome 访问，判断后端服务是否启动
     *
     * @return 字符串 welcome
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 登录
     * @param code
     * @return Map
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(String code) {
        return dingTalkLoginService.login(code);
    }

    /**
     * 绑定用户
     * @param dingUserVO 用户信息
     * @return Map
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> addUser(@RequestBody DingUserVO dingUserVO) {
        return iDingTalkLoginService.addUser(dingUserVO);
    }

    /**
     * 获取corpId
     * @return
     */
    @RequestMapping(value = "/getCorpId", method = RequestMethod.GET)
    public String getCorpId() {
        return AppConfig.getCorpId();
    }
}
