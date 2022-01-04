import Vue from 'vue'
import VueRouter from 'vue-router'
import { DingTalkUtils } from "@/utils/dingTalk"
import { setSessionStorage } from "@/utils/common"
import { get } from "@/utils/ajax"


import Login from '@/views/login/index.vue'
import Info from '@/views/login/info.vue'




Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/info',
        name: 'info',
        component: Info,
    },
]

const router = new VueRouter({
    routes
})

//路由拦截
router.beforeEach((to, from, next) => {
    if(to.path == "/") {
        get(`/api/demo/getCorpId`,{}).then(response => {
            let corpId = response;
            DingTalkUtils.getAuthCode(corpId).then((code) => {
                get(`/api/demo/login`,{ code: code }).then(res => {
                    if(res.code == 1) {
                        const bindInfo = {
                            dingUserName: res.data.dingUserName,
                            userName: res.data.userName,
                        }
                        setSessionStorage("bindInfo", bindInfo)
                        next('/info')
                    }else if(res.code == 2){
                        const data = {
                            dingUserId: res.dingUserId,
                            dingUserName: res.name
                        }
                        setSessionStorage("data", data)
                        next('/login')
                    }
                }).catch(error => {
                    console.log("error",error)
                });
            }).catch((err) => {
                console.log("err",err)
            });
        }).catch((err) => {
            console.log("err",err)
        });
    }else {
        next()
    }
});

export default router
