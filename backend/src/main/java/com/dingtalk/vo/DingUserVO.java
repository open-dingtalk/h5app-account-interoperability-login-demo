package com.dingtalk.vo;


/**
 * @Author lpc
 * @Date 2021/12/23 21:40
 * @Version 1.0
 */
public class DingUserVO {
    /**
     * 钉钉用户id
     */
    private String dingUserId;
    /**
     * 钉钉用户名
     */
    private String dingUserName;
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public String getDingUserId() {
        return dingUserId;
    }

    public void setDingUserId(String dingUserId) {
        this.dingUserId = dingUserId;
    }

    public String getDingUserName() {
        return dingUserName;
    }

    public void setDingUserName(String dingUserName) {
        this.dingUserName = dingUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
