import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import '../static/iconfont/iconfont.css';
import "babel-polyfill";

Vue.use(ElementUI, {size: 'small'});
//开启debug模式
// Vue.config.debug = true;
axios.defaults.withCredentials = true;

let basURL = "http://adminapi.tunnel.qydev.com/admin/api";

//基本配置的axios
let http = axios.create({
    baseURL: basURL,
    timeout: 10000,
    params: {}
    // headers: {'Content-Type': 'application/json; charset=utf-8'}
});
http.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        router.push({
            path: '/login',
            query: {redirect: router.currentRoute.fullPath} //从哪个页面跳转
        });
        return Promise.reject(error);
    });

Vue.prototype.$http = http;

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        const role = localStorage.getItem('ms_username');
        if (!role && to.path !== '/login') {
            next('/login');
        } else {
            next();
        }
    } else {
        next() // 确保一定要调用 next()
    }
})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
