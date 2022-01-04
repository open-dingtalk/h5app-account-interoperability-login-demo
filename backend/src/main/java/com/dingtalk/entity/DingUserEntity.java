package com.dingtalk.entity;

import lombok.Data;

/**
 * @Author lpc
 * @Date 2021/12/23 17:10
 * @Version 1.0
 */
@Data
public class DingUserEntity {
    /**
     * id
     */
    private int id;

    /**
     * 钉钉用户id
     */
    private String dingUserId;

    /**
     * 钉钉用户名
     */
    private String dingUserName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}
