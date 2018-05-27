export default {
    data() {
        return {
            listUrl: '/good/list',
            deleteUrl: '/good/delete',
            recommendUrl: '/good/recommend',
            cancelRecommendUrl: '/good/cancelRecommend',
            search_keyword: '',
            tableData: [],
            delVisible: false,
            delIds: [],
            recommendIds: [],
            cancelRecommendIds: [],
            total: 0,
            pageSize: 5,
            curPage: 1
        }
    },
    created() {
        this.getData();
    },
    computed: {},
    watch: {
        // '$route'(to, from) {
        //     this.getData();
        // }
    },
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
        recommendAll() {
            this.$http.get(this.recommendUrl,
                {
                    params: {
                        ids: this.recommendIds
                    }
                }
            ).then((res) => {
                if (res.data.code == 0) {
                    this.recommendIds = []
                    this.$message({
                        message: '推荐成功！',
                        type: 'success'
                    });
                    this.getData();
                } else {
                    this.$message({
                        message: '推荐失败！',
                        type: 'error'
                    });
                }
            });
        },
        recommendOne(id) {
            this.recommendIds = [];
            this.recommendIds.push(id);
            this.recommendAll();
        },
        cancelRecommendOne(id) {
            this.cancelRecommendIds = [];
            this.cancelRecommendIds.push(id);
            this.cancelRecommendAll();
        },
        cancelRecommendAll() {
            this.$http.get(this.cancelRecommendUrl,
                {
                    params: {
                        ids: this.cancelRecommendIds
                    }
                }
            ).then((res) => {
                if (res.data.code == 0) {
                    this.cancelRecommendIds = []
                    this.$message({
                        message: '取消推荐成功！',
                        type: 'success'
                    });
                    this.getData();
                } else {
                    this.$message({
                        message: '取消推荐失败！',
                        type: 'error'
                    });
                }
            });
        },
        activityAll() {

        },
        activityOne() {

        },
        detailGood(id) {
            this.$router.push({
                name: 'detailGood',
                params: {
                    goodId: id
                }
            });
        },
        editGood(id) {
            this.$router.push({
                name: 'editGood',
                params: {
                    goodId: id
                }
            });
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
                    this.recommendIds.push(records[i].id);
                    this.cancelRecommendIds.push(records[i].id);
                }
            } else {
                this.delIds = [];
                this.recommendIds = [];
                this.cancelRecommendIds = [];
            }
        }
    }
}
