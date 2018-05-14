<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="16">
                        <div class="grid-cont-left">
                            <el-button type="primary" class="el-icon-plus mr10" @click="addGoodType"> 添加</el-button>
                            <el-button type="danger" class="el-icon-delete mr10" @click="deleteAll"> 删除</el-button>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <el-input placeholder="请输入商品类别名称" v-model="search_keyword" class="input-with-select">
                            <el-button type="primary" slot="append" class="el-icon-search" @click="search"> 搜索</el-button>
                        </el-input>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" prop="id" width="55"></el-table-column>
                <el-table-column prop="name" label="名称" width="120">
                </el-table-column>
                <el-table-column prop="parentName" label="父类别" width="120">
                </el-table-column>
                <el-table-column prop="ctime" label="创建时间">
                </el-table-column>
                <el-table-column prop="utime" label="修改时间">
                </el-table-column>
                <el-table-column prop="description" label="描述">
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
<!--<script src="../../../static/moment/moment.min.js'"></script>-->
<script>
    // import moment from "I:\\体验酒柜\\app-liquor-admin\\app-view\\static\\moment\\moment.js'";
    export default {
        data() {
            return {
                findAllUrl: '/goodCategory/findAll',
                listUrl: '/goodCategory/list',
                saveUrl: '/goodCategory/save',
                deleteUrl: '/goodCategory/delete',
                search_keyword: '',
                tableData: [],
                parentTypes: [],
                delIds: [],
                total: 0,
                pageSize: 5,
                curPage: 1,
                delVisible: false,
                editVisible: false,
                addVisible: false,
                addForm: {
                    name: '',
                    parentId: '',
                    description: ''
                },
                editForm: {
                    id: 0,
                    name: '',
                    parentId: '',
                    description: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入商品类别名称', trigger: 'blur'},
                        {max: 15, message: '商品类别名称不超过15个字符', trigger: 'blur'}
                    ],
                    description: [
                        {max: 100, message: '商品类别描述不超过100个字符', trigger: 'blur'}
                    ]
                }
            }
        },
        created() {
            this.getData();
        },
        computed: {},
        methods: {
            // 分页当前页改变
            handleCurrentChange(val) {
                this.curPage = val;
                this.getData();
            },
            // 分页分页大小改变
            handleSizeChange(val) {
                this.pageSize = val;
                this.getData();
            },
            //加载表格数据
            getData() {
                this.$http.get(this.listUrl, {
                    params: {
                        pageNo: this.curPage,
                        pageSize: this.pageSize,
                        searchKeyword: this.search_keyword
                    }
                }).then((res) => {
                    this.tableData = res.data.data;
                    this.total = res.data.total;
                })
            },
            search() {
                this.getData();
            },
            //弹出添加商品类别信息页面
            addGoodType() {
                this.addVisible = true;
                this.$http.get(this.findAllUrl).then((res) => {
                    this.parentTypes = res.data;
                })
            },
            //保存商品类别信息
            saveGoodType(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$http.post(this.saveUrl, {
                            name: this.addForm.name,
                            parentId: this.addForm.parentId,
                            description: this.addForm.description
                        }).then((res) => {
                            if (res.status == 200) {
                                this.getData();
                                this.addVisible = false;
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            //弹出编辑商品类别页面
            editGoodType(index) {
                const item = this.tableData[index];
                this.editForm = {
                    id: item.id,
                    name: item.name,
                    parentId: item.parentId,
                    description: item.description,
                };
                this.$http.get(this.findAllUrl).then((res) => {
                    this.parentTypes = res.data;
                });
                this.editVisible = true;
            },
            // 更新商品类别信息
            updateGoodType(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$http.post(this.saveUrl, {
                            id: this.editForm.id,
                            name: this.editForm.name,
                            parentId: this.editForm.parentId,
                            description: this.editForm.description
                        }).then((res) => {
                            if (res.status == 200) {
                                this.$message({
                                    message: '修改成功！',
                                    type: 'success'
                                });
                                this.getData();
                                this.editVisible = false;
                            } else {
                                this.$message({
                                    message: '修改失败！',
                                    type: 'error'
                                });
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            //删除指定商品类别
            deleteOne(index) {
                this.delIds = [];
                const item = this.tableData[index];
                this.delIds.push(item.id);
                this.delVisible = true;
            },
            //删除指定商品类别
            deleteAll() {
                this.delVisible = true;
            },
            //删除选中商品类别
            confirmDel() {
                this.delVisible = false;
                this.$http.get(this.deleteUrl,
                    {
                        params: {
                            ids: this.delIds
                        }
                    }
                ).then((res) => {
                    if (res.status == 200) {
                        this.delIds = []
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        this.getData();
                    } else {
                        this.$message({
                            message: '删除失败！',
                            type: 'error'
                        });
                    }
                });
            },
            handleSelectionChange(records) {
                if (records.length > 0) {
                    for (let i = 0; i < records.length; i++) {
                        this.delIds.push(records[i].id);
                    }
                } else {
                    this.delIds = [];
                }
            }
        }
        // },
        // filters: {
        //     dateFrm: function (el) {
        //         return moment(el).format("YYYY-MM-DD HH:mm:ss")
        //     }
        // }
    }
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center
    }
</style>
