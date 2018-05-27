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
                <el-collapse-item title="商品基本信息" name="1">
                    <p>商品名称：{{goodInfo.name}}</p>
                    <p>商品类别：{{goodInfo.categoryName}}</p>
                    <p>商品编码：{{goodInfo.code}}</p>
                    <p>商品价格：{{goodInfo.price}}</p>
                    <p v-if="goodInfo.recommend==0">是否推荐商品：未推荐
                    </p>
                    <p v-else="goodInfo.recommend==1">是否推荐商品：已推荐
                    </p>
                    <p>商品销量：{{goodInfo.saleCnt}}</p>
                    <p>创建时间：{{goodInfo.ctime}}</p>
                    <p>创建人：{{goodInfo.createName}}</p>
                    <p>修改时间：{{goodInfo.utime}}</p>
                    <p>修改人：{{goodInfo.updateName}}</p>
                    <!--<p>EMS运费：{{goodInfo.emsFreight}} (单位/元)</p>-->
                    <p>快递运费：{{goodInfo.expressFreight}} (单位/元)</p>
                    <!--<p>平邮运费：{{goodInfo.mailFreight}} (单位/元)</p>-->
                </el-collapse-item>
                <!--<el-col :span="12">-->
                <!--<el-collapse-item title="商品属性信息" name="2">-->

                <!--</el-collapse-item>-->
                <!--</el-col>-->
                <el-collapse-item title="商品首页轮播图片" name="2">
                    <el-upload
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :disabled="true"
                        :file-list="carouselFileList">
                    </el-upload>
                </el-collapse-item>
                <el-collapse-item title="商品列表页图片" name="3">
                    <el-upload
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :disabled="true"
                        :file-list="listFileList">
                    </el-upload>
                </el-collapse-item>
                <el-collapse-item title="商品详情页图片" name="4">
                    <el-upload
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :disabled="true"
                        :file-list="detailFileList">
                    </el-upload>
                </el-collapse-item>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
            </el-collapse>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                detailUrl: '/good/detail',
                activeNames: ['1', '2', '3', '4'],
                goodId: '',
                goodInfo: {},
                carouselFileList: [],
                listFileList: [],
                detailFileList: [],
                dialogImageUrl: '',
                dialogVisible: false
            };
        },
        created() {
            this.goodId = this.$route.params.goodId;
            this.getData();
        },
        watch: {
            '$route': function () {
                this.goodId = '';
                this.goodInfo = {};
                this.goodId = this.$route.params.goodId;
                this.getData();
            }
        },
        methods: {
            //加载表格数据
            getData() {
                this.$http.get(this.detailUrl, {
                    params: {
                        id: this.goodId
                    }
                }).then((res) => {
                    this.carouselFileList = res.data.data.carouselPictures;
                    this.listFileList = res.data.data.goodListPictures;
                    this.detailFileList = res.data.data.goodDetailPictures;
                    this.goodInfo = res.data.data.good;
                });
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            }
        }
    }
</script>
<style scoped>
    .el-upload--picture-card {
        display: none;
        background-color: #fbfdff;
        border: 1px dashed #c0ccda;
        border-radius: 6px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        width: 148px;
        height: 148px;
        line-height: 146px;
        vertical-align: top;
    }
</style>
