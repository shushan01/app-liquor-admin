export default {
    data() {
        return {
            detailUrl: '/good/detail',
            activeNames: ['1', '2', '3', '4'],
            goodId: '',
            goodInfo: {},
            carouselFileList: [],
            listFileList: [],
            detailFileList: [],
            dialogImageUrl: '',
            dialogVisible: false
        };
    },
    created() {
        this.goodId = this.$route.params.goodId;
        this.getData();
    },
    watch: {
        '$route': function () {
            this.goodId = '';
            this.goodInfo = {};
            this.goodId = this.$route.params.goodId;
            this.getData();
        }
    },
    methods: {
        //加载表格数据
        getData() {
            this.$http.get(this.detailUrl, {
                params: {
                    id: this.goodId
                }
            }).then((res) => {
                this.carouselFileList = res.data.data.carouselPictures;
                this.listFileList = res.data.data.goodListPictures;
                this.detailFileList = res.data.data.goodDetailPictures;
                this.goodInfo = res.data.data.good;
            });
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        }
    }
}
