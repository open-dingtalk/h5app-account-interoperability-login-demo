import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// vant组件
import Vant from 'vant'
import 'vant/lib/index.css'
Vue.use(Vant)


// vConsole
if (SystemConfig.IS_DEBUG) {
    const VConsole = require("vconsole");
    new VConsole();
}

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
