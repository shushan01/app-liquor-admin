<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 商品信息管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加商品基本信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form :model="addForm" ref="addForm" :rules="rules" label-width="150px">
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="name" label="名称">
                        <el-input v-model="addForm.name" placeholder="请输入商品名称"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item label="类别">
                        <el-select v-model="addForm.categoryId" placeholder="请选类型">
                            <template v-for="category in goodCategorys" :label="category.name" :value="category.id">
                                <el-option :label="category.name" :value="category.id">{{category.name}}</el-option>
                            </template>
                        </el-select>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="price" label="价格">
                        <el-input v-model="addForm.price" placeholder="请输入商品价格"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item label="推荐商品">
                        <el-select v-model="addForm.recommend" placeholder="请选是否是推荐商品">
                            <el-option :label="是" :value="1">是</el-option>
                            <el-option :label="否" :value="0">否</el-option>
                        </el-select>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="weigth" label="重量">
                        <el-input v-model="addForm.weigth" placeholder="请输入商品重量"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item label="活动状态">
                        <el-select v-model="addForm.activityStatus" placeholder="活动状态">
                            <el-option :label="是" :value="1">是</el-option>
                            <el-option :label="否" :value="0">否</el-option>
                        </el-select>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="currentPrice" label="当前价格">
                        <el-input v-model="addForm.currentPrice" placeholder="请输入商品当前价格"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item label="特价状态">
                        <el-select v-model="addForm.bargainStatus" placeholder="特价状态">
                            <el-option :label="是" :value="1">是</el-option>
                            <el-option :label="否" :value="0">否</el-option>
                        </el-select>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="emsFreight" label="EMS运费">
                        <el-input v-model="addForm.emsFreight" placeholder="请输入商品EMS运费"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="expressFreight" label="快递运费">
                        <el-input v-model="addForm.expressFreight" placeholder="请输入商品快递运费"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="mailFreight" label="平邮运费">
                        <el-input v-model="addForm.mailFreight" placeholder="请输入商品平邮运费"></el-input>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                    <el-form-item prop="stockCnt" label="库存量">
                        <el-input v-model="addForm.stockCnt" placeholder="请输入商品库存量"></el-input>
                        <!--<el-input-number v-model="addForm.stockCnt" placeholder="请输入商品库存量" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>-->
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <!--<el-row>-->
                    <!--<el-col :span="12">-->
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">表单提交</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                    <!--</el-col>-->
                    <!--</el-row>-->
                </el-form>
            </div>
        </div>
    </div>
</template>
<script src="../../../../static/js/addGood.js"></script>
