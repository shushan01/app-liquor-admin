<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="18">
                        <div class="grid-cont-left">
                            <el-button type="primary" class="el-icon-plus mr10" @click="addGoodInfo"> 添加商品基本信息</el-button>
                            <el-button type="primary" class="el-icon-plus mr10" @click="addGoodAttr"> 添加商品属性信息</el-button>
                            <el-button type="danger" class="el-icon-delete mr10" @click="deleteAll"> 删除</el-button>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <el-input placeholder="请输入商品名称" v-model="search_keyword" class="input-with-select">
                            <el-button type="primary" slot="append" class="el-icon-search" @click="search"> 搜索</el-button>
                        </el-input>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" prop="id" width="55"></el-table-column>
                <el-table-column show-overflow-tooltip="true" prop="name" label="名称">
                </el-table-column>
                <el-table-column prop="code" label="编码">
                </el-table-column>
                <el-table-column prop="code" label="类别">
                </el-table-column>
                <el-table-column prop="code" label="点击量">
                </el-table-column>
                <el-table-column prop="code" label="收藏量">
                </el-table-column>
                <el-table-column prop="code" label="库存量">
                </el-table-column>
                <el-table-column prop="code" label="销售量">
                </el-table-column>
                <el-table-column prop="code" label="是否推荐商品">
                </el-table-column>
                <el-table-column prop="code" label="重量">
                </el-table-column>
                <el-table-column prop="code" label="活动状态">
                </el-table-column>
                <el-table-column prop="code" label="特价状态">
                </el-table-column>
                <el-table-column prop="code" label="现价">
                </el-table-column>
                <el-table-column prop="ctime" label="创建时间">
                </el-table-column>
                <el-table-column prop="utime" label="修改时间">
                </el-table-column>
                <el-table-column prop="description" label="描述">
                </el-table-column>
                <el-table-column label="操作" width="270">
                    <template slot-scope="scope">
                        <el-button size="small" class="el-icon-view" @click="detailGood(scope.$index)"> 详情</el-button>
                        <el-button size="small" class="el-icon-edit" @click="editGood(scope.$index)"> 编辑</el-button>
                        <el-button type="danger" class="el-icon-delete mr10" @click="deleteOne(scope.$index)"> 删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-sizes="[5,10, 20, 30, 40,50]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next"
                    :total="total">
                </el-pagination>
            </div>
        </div>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmDel">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script src="../../../../static/js/goodManager.js"></script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center
    }
</style>
