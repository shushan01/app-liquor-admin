import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: {title: '自述文件', keepAlive: false, requiresAuth: true},
            children: [
                {
                    path: '/index',
                    component: resolve => require(['../components/page/index.vue'], resolve),
                    meta: {title: '系统首页', keepAlive: false, requiresAuth: true}
                },
                {
                    path: '/goodTypeManager',
                    component: resolve => require(['../components/page/goodType/goodTypeManager.vue'], resolve),
                    meta: {title: '商品类别管理', keepAlive: false, requiresAuth: true}
                },
                {
                    path: '/goodManager',
                    component: resolve => require(['../components/page/good/goodManager.vue'], resolve),
                    meta: {title: '商品信息管理', keepAlive: false, requiresAuth: true}
                },
                {
                    path: '/addGood',
                    component: resolve => require(['../components/page/good/addGood.vue'], resolve),
                    meta: {title: '添加商品信息', keepAlive: false, requiresAuth: true}
                },
                {
                    name: 'editGood',
                    path: '/editGood',
                    component: resolve => require(['../components/page/good/editGood.vue'], resolve),
                    meta: {title: '修改商品信息', keepAlive: false, requiresAuth: true}
                },
                {
                    name: 'detailGood',
                    path: '/detailGood',
                    component: resolve => require(['../components/page/good/detailGood.vue'], resolve),
                    meta: {title: '商品详细信息', keepAlive: false, requiresAuth: true}
                },
                {
                    name: 'orderManager',
                    path: '/orderManager',
                    component: resolve => require(['../components/page/order/orderManager.vue'], resolve),
                    meta: {title: '订单管理', keepAlive: false, requiresAuth: true}
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve),
            meta: {keepAlive: false}
        }
    ]
})
