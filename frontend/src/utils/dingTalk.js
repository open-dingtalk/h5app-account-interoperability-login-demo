import * as dd from 'dingtalk-jsapi'


const DingTalkUtils = {

    // 获取临时授权码
    getAuthCode(aliCorpId) {
        return new Promise(function (resolve, reject) {
            dd.ready(function () {
                dd.runtime.permission.requestAuthCode({
                    corpId: aliCorpId,
                    onSuccess: function (result) {
                        resolve(result.code);
                    },
                    onFail: function (err) {
                        reject(err);
                    }
                });
            });
        });
    },

    // 设置钉钉title
    setNavTitle(title) {
        dd.ready(function () {
            dd.biz.navigation.setTitle({
                title: title,//控制标题文本，空字符串表示显示默认文本
                onSuccess: function (result) {
                },
                onFail: function (err) {
                }
            });
        });
    },

    // 关闭当前页面 
    close() {
        dd.ready(function () {
            dd.biz.navigation.close({
                onSuccess : function(result) {
                    /*result结构
                    {}
                    */
                },
                onFail : function(err) {}
            })
        })
    }

}

export { DingTalkUtils }