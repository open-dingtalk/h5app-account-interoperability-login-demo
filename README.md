# 钉钉SSO登陆绑定Demo
***
## Getting Started
***
1. 需要有一个钉钉注册企业，如果没有可以创建：https://oa.dingtalk.com/register_new.htm#/

2. 成为钉钉开发者，参考文档：https://developers.dingtalk.com/document/app/become-a-dingtalk-developer

3. 登录钉钉开放平台后台创建一个H5应用： https://open-dev.dingtalk.com/#/index

4. 配置应用

+ 配置开发管理，参考文档：https://developers.dingtalk.com/document/app/configure-orgapp

![img_1.png](img_1.png)
### 克隆代码到本地
git clone

`git clone xxxxxxxxxx`
### 开发
demo必须在钉钉环境下才可以获取到钉钉用户信息，在浏览器中不支持获取。
***
## 使用开发者工具
### 下载开发者工具
https://www.jetbrains.com/idea/download/#section=windows

打开idea，导入项目，打包部署

ps：项目必须部署并在钉钉环境配置微应用才可以登陆调用，否则无法获取钉钉用户信息！！！

