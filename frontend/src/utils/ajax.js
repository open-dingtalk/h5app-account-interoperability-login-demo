import axios from 'axios'


axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.withCredentials = true;
axios.defaults.timeout = 15000; // 设置超时时间

//请求拦截器
axios.interceptors.request.use(config => {
    return config;
}, error => {
    return Promise.reject(error);
});

//响应拦截器
axios.interceptors.response.use(function (response) {
    if(response.data.result == 1004) {
        router.push('/login').then(r => {});
    }
    return response;
}, function (error) {
    if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout') !== -1) {
        console.error('请求超时...');
        return {
            code: -1,
            msg: '请求超时，请稍候再试!'
        };
    } else {
        console.error('出现异常:' + error.code, error);
        return Promise.reject(error);
    }
});


/**get请求数据
 * @param uri 请求地址
 * @param args 请求参数
 * @returns {Promise}
 */
function get(uri, args) {
    return new Promise(function (resolve, reject) {
        axios.get(uri, {params: args }).then(response => {
            resolve(response.data);
        }).catch(error => {
            reject(error);
        });
    });
}

/**
 * POST请求数据
 * @param uri 请求地址
 * @param args 请求参数
 * @returns {Promise}
 */
function post(uri, args) {
    return new Promise(function (resolve, reject) {
        axios.post(uri, args).then(function (response) {
            resolve(response.data);
        }).catch(function (error) {
            reject(error);
        });
    });
}

export { get, post }