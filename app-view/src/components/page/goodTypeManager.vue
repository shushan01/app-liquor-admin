<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="12">
                        <div class="grid-cont-left">
                            <el-button type="primary" class="el-icon-plus mr10"> 添加</el-button>
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
                        <el-button size="small" class="el-icon-edit" @click="handleEdit(scope.$index)">编辑</el-button>
                        <el-button type="danger" class="el-icon-delete mr10"> 删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="210">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" label-width="50px">
                <el-input v-model="form.id" type="hidden"></el-input>
                <el-form-item label="名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="form.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
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
                cur_page: 1,
                delVisible: false,
                editVisible: false,
                form: {
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
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            //加载表格数据
            getData() {
                this.$http.post(this.url, {
                    page: this.cur_page
                }).then((res) => {
                    this.tableData = res.data.list;
                })
            },
            //编辑页面
            handleEdit(index) {
                const item = this.tableData[index];
                this.form = {
                    id: item.id,
                    name: item.name,
                    description: item.description,
                }
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                console.log(232);
                this.$http.get('/goodType/update', {
                    params: {
                        id: this.form.id,
                        name: this.form.name,
                        description: this.form.description
                    }
                }).then((res) => {
                    console.log(res);
                })
                this.getData();
                this.editVisible = false;
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
