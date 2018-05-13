<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="12">
                        <div class="grid-cont-left">
                            <el-button type="primary" class="el-icon-plus mr10" @click="addGoodType"> 添加</el-button>
                            <el-button type="danger" class="el-icon-delete mr10"> 删除</el-button>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="grid-cont-right">
                            <el-input v-model="search_keyword" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                            <el-button type="primary" class="el-icon-search"> 搜索</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" prop="id" width="55"></el-table-column>
                <el-table-column prop="name" label="名称" width="120">
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
                    layout="sizes, prev, pager, next"
                    :total="total">
                </el-pagination>
            </div>
        </div>

        <!-- 添加弹出框 -->
        <el-dialog title="添加" :visible.sync="addVisible" width="30%">
            <el-form ref="addForm" label-width="50px">
                <el-form-item label="名称">
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="addForm.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveGoodType">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="editForm" label-width="50px">
                <el-input v-model="editForm.id" type="hidden"></el-input>
                <el-form-item label="名称">
                    <el-input v-model="editForm.name"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="editForm.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateGoodType">确 定</el-button>
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
<script>
    export default {
        data() {
            return {
                url: '/goodType/list',
                search_keyword: '',
                tableData: [],
                delIds: [],
                total: 0,
                pageSize: 5,
                curPage: 1,
                delVisible: false,
                editVisible: false,
                addVisible: false,
                addForm: {
                    name: '',
                    description: ''
                },
                editForm: {
                    id: 0,
                    name: '',
                    description: ''
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
                this.$http.post(this.url, {
                    page: this.curPage
                }).then((res) => {
                    this.tableData = res.data.list;
                    this.total = res.data.total;
                })
            },
            //弹出添加商品类别信息页面
            addGoodType() {
                console.log(999)
                this.addVisible = true;
            },
            //保存商品类别信息
            saveGoodType() {
                console.log(this.addForm.name)
                console.log(this.addForm.description)
                this.addVisible = false;
            },
            //弹出编辑商品类别页面
            editGoodType(index) {
                const item = this.tableData[index];
                this.editForm = {
                    id: item.id,
                    name: item.name,
                    description: item.description,
                }
                this.editVisible = true;
            },
            //删除指定商品类别
            deleteOne(index) {
                const item = this.tableData[index];
                console.log(item.id);
            },
            //删除选中商品类别
            confirmDel() {
                console.log(item.id);
            },
            // 更新商品类别信息
            updateGoodType() {
                this.$http.post('/goodType/update', {
                    id: this.form.id,
                    name: this.form.name,
                    description: this.form.description
                }).then((res) => {
                    console.log(res);
                })
                this.getData();
                this.editVisible = false;
                // this.$http.get('/goodType/update', {
                //     params: {
                //         id: this.form.id,
                //         name: this.form.name,
                //         description: this.form.description
                //     }
                // }).then((res) => {
                //     console.log(res);
                // })
                // this.getData();
                // this.editVisible = false;
            }
        }
    }
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center
    }
</style>
