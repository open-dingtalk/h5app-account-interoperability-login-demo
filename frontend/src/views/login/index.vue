<template>
    <div class="login">
        <div class="title">绑定</div>
        <van-form @submit="onSubmit" ref="form">
            <van-field
                v-model="form.userName"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
            />
            <van-field
                v-model="form.password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
            />
            <div class="btn">
                <van-button round block type="info" native-type="submit">绑 定</van-button>
            </div>
        </van-form>
    </div>
</template>
 
<script>
import { DingTalkUtils } from "@/utils/dingTalk"
import { post } from "@/utils/ajax"
import { setSessionStorage, getSessionStorage } from "@/utils/common"

export default {
    name: 'Login',
    components: {

    },
    props: {
 
    },
    data () {
        return {
            form: {
                userName: "",
                password: ""
            }
        }
    },
    methods: {
        onSubmit() {
            this.$refs.form.validate().then( async()=>{
                const data = getSessionStorage("data")
                const params = Object.assign(data, this.form)
                const res = await post(`/api/demo/add`, params)
                if(res.code == 1) {
                    const bindInfo = {
                        dingUserName: res.dingUserName,
                        userName: res.userName
                    }
                    setSessionStorage("bindInfo", bindInfo)
                    this.$router.push(`/info`)
                }
            })
        }
    },
    watch: {},
    computed: {},
    created () {
        DingTalkUtils.setNavTitle("OA登录DEMO")
    },
    mounted () {
    }
}
</script>
 
<style lang="scss" scoped>
.login {
    height: 100%;
    padding: 0 15px;
    box-sizing: border-box;
    border-top: 1px solid #f0f0f0;
    .title {
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        margin-top: 40px;
    }
    .van-form {
        margin-top: 70px;
        .btn {
            margin-top: 90px;
            font-size: 16px;
        }
    }

}
</style>