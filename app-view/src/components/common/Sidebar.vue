<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
                 text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i>{{ item.title }}
                        </template>
                        <el-menu-item v-for="subItem in item.subs" :key="subItem.index" :index="subItem.index">
                            <i :class="subItem.icon"></i>{{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>{{ item.title }}
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';

    export default {
        data() {
            return {
                collapse: false,
                items: [
                    {
                        icon: 'icon-shouye',
                        index: 'index',
                        title: '系统首页'
                    },
                    {
                        icon: 'el-icon-goods',
                        index: '1',
                        title: '商品管理',
                        subs: [
                            {
                                icon: 'icon-shangpin',
                                index: 'goodTypeManager',
                                title: '商品类别管理'
                            },
                            {
                                icon: 'icon-wutushangpin',
                                index: 'goodManager',
                                title: '商品信息管理'
                            }
                        ]
                    },
                    {
                        icon: 'icon-dingdan',
                        index: 'orderManager',
                        title: '订单管理'
                    },
                    {
                        icon: 'icon-wuliuxiaocheche',
                        index: 'logisticManager',
                        title: '物流管理'
                    },
                    {
                        icon: 'el-icon-setting',
                        index: '2',
                        title: '系统管理',
                        subs: [
                            // {
                            //     icon: 'icon-drxx11',
                            //     index: 'menuManager',
                            //     title: '菜单管理',
                            // },
                            {
                                icon: 'icon-drxx10',
                                index: 'userManager',
                                title: '用户管理',
                            },
                            {
                                icon: 'icon-drxx88',
                                index: 'roleManager',
                                title: '角色管理'
                            },
                            {
                                icon: 'icon-quanxianguanli',
                                index: 'authorityManager',
                                title: '权限管理'
                            }
                        ]
                    }
                ]
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace('/', '');
            }
        },
        created() {
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar {
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom: 0;
    }

    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }

    .sidebar > ul {
        height: 100%;
    }
</style>
