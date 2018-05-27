export default {
    data() {
        return {
            listUrl: '/order/list',
            search_keyword: '',
            tableData: [],
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
        '$route'(to, from) {
            this.tableData = [];
            this.getData();
        }
    },
    methods: {
        //加载表格数据
        getData() {
        }
    }
}
