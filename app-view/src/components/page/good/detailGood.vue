<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 商品信息管理</el-breadcrumb-item>
                <el-breadcrumb-item>商品详情</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-collapse v-model="activeNames">
                <el-row>
                    <el-col :span="12">
                        <el-collapse-item title="商品基本信息" name="1">
                            {{goodInfo.name}}
                        </el-collapse-item>
                    </el-col>
                    <el-col :span="12">
                        <el-collapse-item title="商品属性信息" name="2">
                            <div>简化流程：设计简洁直观的操作流程；</div>
                            <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
                            <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
                        </el-collapse-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-collapse-item title="商品图片信息" name="3">
                            <el-row>
                                <template v-for="file in fileList" :label="file.name" :value="file.url">
                                    <el-col :span="12">
                                        <img :src="file.url"/>
                                    </el-col>
                                </template>
                            </el-row>
                        </el-collapse-item>
                    </el-col>
                </el-row>
            </el-collapse>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                detailUrl: '/good/detail',
                activeNames: ['1', '2'],
                goodId: '',
                goodInfo: {},
                fileList: [],
            };
        },
        created() {
            this.goodId = this.$route.params.goodId;
            this.getData();
        },
        methods: {
            //加载表格数据
            getData() {
                this.$http.get(this.detailUrl, {
                    params: {
                        id: this.goodId
                    }
                }).then((res) => {
                    this.fileList = res.data.data.pictures;
                    this.goodInfo = res.data.data.good;
                    console.log(this.fileList)
                    console.log(this.goodInfo)
                });
            }
        }
    }
</script>
