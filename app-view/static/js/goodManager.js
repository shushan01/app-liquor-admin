export default {
    data() {
        return {
            listUrl: '/good/list',
            deleteUrl: '/good/delete',
            detailUrl: '/good/detail',
            search_keyword: '',
            tableData: [],
            delVisible: false,
            delIds: [],
            total: 0,
            pageSize: 5,
            curPage: 1
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
        activityStatusFmt(row, column) {
            var status = row.activityStatus;
            if (status == 0) {
                return "未活动"
            } else {
                return "活动中"
            }
        },
        recommendFmt(row, column) {
            var recommend = row.recommend;
            if (recommend == 0) {
                return "未推荐"
            } else {
                return "已推荐"
            }
        },
        search() {
            this.getData();
        },
        //弹出添加商品类别信息页面
        addGoodInfo() {
            this.$router.push('/addGood');
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
