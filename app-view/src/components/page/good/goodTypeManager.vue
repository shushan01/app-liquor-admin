<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="18">
                        <div class="grid-cont-left">
                            <el-button type="primary" class="el-icon-plus mr10" @click="addGoodType"> 添加</el-button>
                            <el-button type="danger" class="el-icon-delete mr10" @click="deleteAll"> 删除</el-button>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <el-input placeholder="请输入商品类别名称" v-model="search_keyword" class="input-with-select">
                            <el-button type="primary" slot="append" class="el-icon-search" @click="search"> 搜索</el-button>
                        </el-input>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" prop="id" width="35"></el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="name" label="名称">
                </el-table-column>
                <el-table-column prop="parentName" label="父类别">
                </el-table-column>
                <el-table-column prop="ctime" label="创建时间">
                </el-table-column>
                <el-table-column prop="utime" label="修改时间">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" prop="description" label="描述">
                </el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="small" class="el-icon-edit" @click="editGoodType(scope.$index)">编辑</el-button>
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

        <!-- 添加弹出框 -->
        <el-dialog title="添加商品类别" :visible.sync="addVisible" width="30%">
            <el-form :model="addForm" ref="addForm" :rules="rules" label-width="100px">
                <el-form-item prop="name" label="名称">
                    <el-input v-model="addForm.name" placeholder="请输入商品类别名称"></el-input>
                </el-form-item>
                <el-form-item label="父类别">
                    <el-select v-model="addForm.parentId" placeholder="请选择父类型">
                        <template v-for="parentType in parentTypes" :label="parentType.name" :value="parentType.id">
                            <el-option :label="parentType.name" :value="parentType.id">{{parentType.name}}</el-option>
                        </template>
                    </el-select>
                </el-form-item>
                <el-form-item prop="description" label="描述">
                    <el-input type="textarea" autosize placeholder="请输入商品类别描述" v-model="addForm.description">
                    </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" class="icon-baocun7" @click="saveGoodType('addForm')"> 保存</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="修改商品类别信息" :visible.sync="editVisible" width="30%">
            <el-form :model="editForm" :rules="rules" ref="editForm" label-width="100px">
                <el-input v-model="editForm.id" type="hidden"></el-input>
                <el-form-item prop="name" label="名称">
                    <el-input v-model="editForm.name" placeholder="请输入商品类别名称"></el-input>
                </el-form-item>
                <el-form-item label="父类别">
                    <el-select v-model="editForm.parentId" placeholder="请选择父类型">
                        <template v-for="parentType in parentTypes" :label="parentType.name" :value="parentType.id">
                            <template v-if="parentType.id==editForm.parentId">
                                <el-option :label="parentType.name" :value="parentType.id" selected>{{parentType.name}}</el-option>
                            </template>
                            <template v-else>
                                <el-option :label="parentType.name" :value="parentType.id">{{parentType.name}}</el-option>
                            </template>
                        </template>
                    </el-select>
                </el-form-item>
                <el-form-item prop="description" label="描述">
                    <el-input type="textarea" autosize placeholder="请输入商品类别描述" v-model="editForm.description">
                    </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" class="icon-baocun7" @click="updateGoodType('editForm')">确 定</el-button>
            </span>
        </el-dialog>

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
<script src="../../../../static/js/good/goodTypeManager.js"></script>
<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center
    }
</style>
