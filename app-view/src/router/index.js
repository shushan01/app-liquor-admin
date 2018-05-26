import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: {title: '自述文件'},
            children: [
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: {title: '系统首页'}
                },
                {
                    path: '/goodTypeManager',
                    component: resolve => require(['../components/page/good/goodTypeManager.vue'], resolve),
                    meta: {title: '商品类别管理', keepAlive: false}
                },
                {
                    path: '/goodManager',
                    component: resolve => require(['../components/page/good/goodManager.vue'], resolve),
                    meta: {title: '商品信息管理', keepAlive: false}
                },
                {
                    path: '/addGood',
                    component: resolve => require(['../components/page/good/addGood.vue'], resolve),
                    meta: {title: '添加商品信息', keepAlive: false}
                },
                {
                    name: 'editGood',
                    path: '/editGood',
                    component: resolve => require(['../components/page/good/editGood.vue'], resolve),
                    meta: {title: '修改商品信息', keepAlive: false}
                },
                {
                    name: 'detailGood',
                    path: '/detailGood',
                    component: resolve => require(['../components/page/good/detailGood.vue'], resolve),
                    meta: {title: '商品详细信息', keepAlive: false}
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        }
    ]
})
