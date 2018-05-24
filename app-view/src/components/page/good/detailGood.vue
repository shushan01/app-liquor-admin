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
                            <p>商品名称：{{goodInfo.name}}</p>
                            <p>商品类别：{{goodInfo.categoryName}}</p>
                            <p>商品编码：{{goodInfo.code}}</p>
                            <p>商品价格：{{goodInfo.price}}</p>
                            <p>商品销量：{{goodInfo.saleCnt}}</p>
                            <p>创建时间：{{goodInfo.ctime}}</p>
                            <p>创建人：{{goodInfo.createName}}</p>
                            <p>修改时间：{{goodInfo.utime}}</p>
                            <p>修改人：{{goodInfo.updateName}}</p>
                            <p>EMS运费：{{goodInfo.emsFreight}} (单位/元)</p>
                            <p>快递运费：{{goodInfo.expressFreight}} (单位/元)</p>
                            <p>平邮运费：{{goodInfo.mailFreight}} (单位/元)</p>
                        </el-collapse-item>
                    </el-col>
                    <el-col :span="12">
                        <el-collapse-item title="商品属性信息" name="2">

                        </el-collapse-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-collapse-item title="商品图片信息" name="3">
                            <el-row>
                                <el-col :span="6" v-for="file in fileList">
                                    <img width="70%;" :src="file.url"/>
                                </el-col>
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
                });
            }
        }
    }
</script>
