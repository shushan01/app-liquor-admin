export default {
    data() {
        return {
            findAllGoodCategoryUrl: '/goodCategory/findAll',
            saveUrl: '/good/save',
            goodCategorys: [],
            fileList: [],
            active: 0,
            baseInfoDisplay: "display: block;",
            uploadPictureDisplay: "display: none;",
            addForm: {
                name: '',
                categoryId: '',
                price: '',
                recommend: '',
                weigth: '',
                activityStatus: '',
                currentPrice: '',
                bargainStatus: '',
                emsFreight: '',
                expressFreight: '',
                mailFreight: '',
                stockCnt: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入商品名称', trigger: 'blur'},
                    {max: 15, message: '商品名称不超过15个字符', trigger: 'blur'}
                ]
            }
        }
    },
    created() {
        this.$http.get(this.findAllGoodCategoryUrl).then((res) => {
            this.goodCategorys = res.data;
        });
    },
    computed: {},
    methods: {
        next() {
            if (this.active == 0) {
                this.baseInfoDisplay = "display: none;";
                this.uploadPictureDisplay = "display: block;";
            }
            if (this.active++ > 2) this.active = 0;
        },
        handleExceed(files, fileList) {
            console.log(files)
            console.log(fileList)
            this.$message.error('上传图片个数超过限制!最多可以上传12张图片。');
        },
        handleSuccess(response, file, fileList) {
            this.fileList = fileList;
            console.log(this.fileList)
        },
        //保存商品别信息
        saveGood(formName) {
            // this.$refs[formName].validate((valid) => {
            //     if (valid) {
            this.$http.post(this.saveUrl, {
                file: this.fileList
            }).then((res) => {
                if (res.status == 200) {
                }
            });
            //     } else {
            //         return false;
            //     }
            // });
        }
    }
}
