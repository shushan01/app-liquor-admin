export default {
    data() {
        return {
            findAllGoodCategoryUrl: '/goodCategory/findAll',
            saveUrl: '/good/save',
            goodCategorys: [],
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
        //保存商品类别信息
        saveGoodType(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.post(this.saveUrl, {
                        name: this.addForm.name
                    }).then((res) => {
                        if (res.status == 200) {
                        }
                    });
                } else {
                    return false;
                }
            });
        }
    }
}
