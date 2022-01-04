<template>
    <div class="info">
        <div class="title">已绑定</div>
        <van-form>
            <van-field
                v-model="bindInfo.userName"
                label="用户名"
                readonly
            />
            <van-field
                v-model="bindInfo.dingUserName"
                type="dingUserName"
                label="钉钉名"
                readonly
            />

            <div class="btn">
                <van-button round block type="info" @click="close">关闭</van-button>
            </div>
        </van-form>
    </div>
</template>
 
<script>
import { DingTalkUtils } from "@/utils/dingTalk"
import { getSessionStorage } from "@/utils/common"


export default {
    name: 'Info',
    components: {

    },
    props: {
 
    },
    data () {
        return {
            bindInfo: {}
        }
    },
    methods: {
        close() {
            DingTalkUtils.close()
        }
    },
    watch: {},
    computed: {},
    created () {
        DingTalkUtils.setNavTitle("OA登录DEMO")
        this.bindInfo = getSessionStorage("bindInfo")
    },
    mounted () {
    }
}
</script>
 
<style lang="scss" scoped>
.info {
    height: 100%;
    padding: 0 30px;
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
        .van-cell {
            padding: 20px 16px;
            font-size: 16px;
            ::v-deep .van-field__label {
                width: 4em;
                display: inline-block;
                text-align: justify;
                text-align-last: justify;
                font-weight: bold;
            }
            ::v-deep .van-field__control {
                margin-left: 20px;
            }
            &::after {
                border-bottom: 0;
            }
        }
        .btn {
            margin-top: 90px;
            font-size: 16px;
        }
    }
}
</style>