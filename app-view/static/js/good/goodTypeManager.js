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
                this.parentTypes = res.data.data;
            })
        },
        //保存商品类别信息
        saveGoodType(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.get(this.saveUrl, {
                        params: {
                            name: this.addForm.name,
                            parentId: this.addForm.parentId,
                            description: this.addForm.description
                        }
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
                this.parentTypes = res.data.data;
            });
            this.editVisible = true;
        },
        // 更新商品类别信息
        updateGoodType(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.get(this.saveUrl, {
                        params: {
                            id: this.editForm.id,
                            name: this.editForm.name,
                            parentId: this.editForm.parentId,
                            description: this.editForm.description
                        }
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
}
