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
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: {title: '基本表单'}
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: {title: '富文本编辑器'}
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: {title: 'markdown编辑器'}
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: {title: '文件上传'}
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: {title: 'schart图表'}
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: {title: '拖拽列表'}
                },
                {
                    // 权限页面
                    path: '/management',
                    component: resolve => require(['../components/page/Management.vue'], resolve),
                    meta: {title: '权限测试', permission: true}
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
