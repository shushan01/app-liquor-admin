<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 商品信息管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加商品</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div style="margin-bottom: 50px;">
                <el-steps :active="active" finish-status="success">
                    <el-step title="添加商品基本信息" icon="el-icon-circle-plus">
                    </el-step>
                    <el-step title="上传商品图片信息" icon="el-icon-upload">
                    </el-step>
                    <!--<el-step title="添加商品属性信息" icon="el-icon-info"></el-step>-->
                    <el-step title="完成" icon="el-icon-success"></el-step>
                </el-steps>
            </div>

            <div class="form-box" :style="baseInfoDisplay">
                <el-form :model="addForm" ref="addForm" :rules="rules" label-width="150px">
                    <el-form-item prop="name" label="名称">
                        <el-input v-model="addForm.name" placeholder="请输入商品名称"></el-input>
                    </el-form-item>
                    <el-form-item label="类别">
                        <el-select v-model="addForm.categoryId" placeholder="请选类型">
                            <template v-for="category in goodCategorys" :label="category.name" :value="category.id">
                                <el-option :label="category.name" :value="category.id">{{category.name}}</el-option>
                            </template>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="price" label="价格">
                        <el-input v-model.number="addForm.price" placeholder="请输入商品价格"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="推荐商品">
                         <el-select v-model="addForm.recommend" placeholder="请选是否是推荐商品">
                             <el-option :label="是" :value="1">是</el-option>
                             <el-option :label="否" :value="0">否</el-option>
                         </el-select>
                     </el-form-item>-->
                    <el-form-item prop="weight" label="重量">
                        <el-input v-model.number="addForm.weight" placeholder="请输入商品重量"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="活动状态">
                         <el-select v-model="addForm.activityStatus" placeholder="活动状态">
                             <el-option :label="是" :value="1">是</el-option>
                             <el-option :label="否" :value="0">否</el-option>
                         </el-select>
                     </el-form-item>-->
                    <!-- <el-form-item prop="currentPrice" label="当前价格">
                         <el-input v-model="addForm.currentPrice" placeholder="请输入商品当前价格"></el-input>
                     </el-form-item>
                     <el-form-item label="特价状态">
                         <el-select v-model="addForm.bargainStatus" placeholder="特价状态">
                             <el-option :label="是" :value="1">是</el-option>
                             <el-option :label="否" :value="0">否</el-option>
                         </el-select>
                     </el-form-item>-->
                    <el-form-item prop="emsFreight" label="EMS运费">
                        <el-input v-model.number="addForm.emsFreight" placeholder="请输入商品EMS运费"></el-input>
                    </el-form-item>
                    <el-form-item prop="expressFreight" label="快递运费">
                        <el-input v-model.number="addForm.expressFreight" placeholder="请输入商品快递运费"></el-input>
                    </el-form-item>
                    <el-form-item prop="mailFreight" label="平邮运费">
                        <el-input v-model.number="addForm.mailFreight" placeholder="请输入商品平邮运费"></el-input>
                    </el-form-item>
                    <!-- <el-form-item prop="stockCnt" label="库存量">
                         <el-input v-model="addForm.stockCnt" placeholder="请输入商品库存量"></el-input>
                     </el-form-item>-->
                </el-form>
            </div>
            <div :style="uploadPictureDisplay">
                <el-collapse>
                    <el-collapse-item title="上传首页轮播图片" name="1">
                        <el-upload
                            :action="uploadUrl"
                            ref="upload"
                            list-type="picture-card"
                            :on-remove="carouselHandleRemove"
                            :on-error="uploadError"
                            :on-preview="handlePictureCardPreview"
                            :auto-upload="true"
                            :with-credentials="true"
                            :data="carouselPageUploadData"
                            :limit="5"
                            :on-exceed="carouselHandleExceed">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-collapse-item>
                    <el-collapse-item title="上传商品列表页图片" name="2">
                        <el-upload
                            :action="uploadUrl"
                            ref="upload"
                            list-type="picture-card"
                            :on-remove="listPageHandleRemove"
                            :on-error="uploadError"
                            :on-preview="handlePictureCardPreview"
                            :auto-upload="true"
                            :with-credentials="true"
                            :data="listPageUploadData"
                            :limit="3"
                            :on-exceed="listPageHandleExceed">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-collapse-item>

                    <el-collapse-item title="上传商品详情页图片" name="3">
                        <el-upload
                            :action="uploadUrl"
                            list-type="picture-card"
                            :on-remove="detailPageHandleRemove"
                            :on-error="uploadError"
                            :on-preview="handlePictureCardPreview"
                            :auto-upload="true"
                            :with-credentials="true"
                            :data="detailPageUploadData"
                            :limit="12"
                            :on-exceed="detailPageHandleExceed">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-collapse-item>

                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                </el-collapse>
            </div>

            <!--<div :style="addAttrDisplay">-->
            <!--添加商品属性-->
            <!--</div>-->
            <div :style="finishDisplay">
                你已经成功添加完善了商品信息<br/>
                <el-button @click="goBack" style="margin-top: 50px;">返回</el-button>
            </div>
            <div style="margin-top: 50px;">
                <!--<el-button :style="prevCss" @click="prev">上一步</el-button>-->
                <el-button :style="nextCss" @click="next">下一步</el-button>
                <el-button :style="finishCss" @click="finish">完成</el-button>
            </div>
        </div>
    </div>
</template>
<script src="../../../../static/js/good/addGood.js"></script>
