<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="6">
                        <el-input placeholder="请输入商品名称" v-model="search_keyword" class="input-with-select">
                            <el-button type="primary" slot="append" class="el-icon-search" @click="search"> 搜索</el-button>
                        </el-input>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" prop="id" width="35"></el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="name" fixed label="下单人" width="90px;">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="price" sortable width="130px;" label="订单总价">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="price" sortable width="100px;" label="实际支付">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="createName" width="100px;" label="订单编码">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="ctime" sortable width="100px;" label="商品数量">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="updateName" sortable width="100px;" label="成交时间">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="updateName" width="100px;" label="订单状态">
                </el-table-column>
                <el-table-column label="操作" width="460" fixed="right">
                    <template slot-scope="scope">
                        <el-button size="small" class="el-icon-view" @click="detailGood(scope.row.id)"> 详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-sizes="[10, 20, 30, 40,50]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next"
                    :total="total">
                </el-pagination>
            </div>
        </div>

    </div>
</template>
<script src="../../../../static/js/order/orderManager.js"></script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }
</style>
