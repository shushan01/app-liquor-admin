export default {
    data() {
        return {
            findAllGoodCategoryUrl: '/goodCategory/findAll',
            saveUrl: '/good/save',
            uploadUrl: 'http://localhost:8080/image/upload',
            deleteImageUrl: '/image/delete',
            goodCategorys: [],
            fileList: [],
            uploadData: {
                ownerId: -1,
                type: 'good'
            },
            active: 0,
            baseInfoDisplay: "display: block;",
            uploadPictureDisplay: "display: none;",
            addAttrDisplay: "display: none;",
            finishDisplay: "display: none;",
            nextCss: "",
            finishCss: "display: none;",
            dialogImageUrl: '',
            dialogVisible: false,
            addForm: {
                name: '',
                categoryId: '',
                price: '',
                weight: '',
                emsFreight: '',
                expressFreight: '',
                mailFreight: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入商品名称', trigger: 'blur'},
                    {max: 15, message: '商品名称不超过15个字符', trigger: 'blur'}
                ],
                price: [
                    {required: true, message: '商品价格不能为空'},
                    {type: 'number', message: '商品价格必须为数字值'}
                ],
                weight: [
                    {required: true, message: '商品重量不能为空'},
                    {type: 'number', message: '商品重量必须为数字值'}
                ],
                emsFreight: [
                    {required: true, message: '商品EMS运费不能为空'},
                    {type: 'number', message: '商品EMS运费必须为数字值'}
                ],
                expressFreight: [
                    {required: true, message: '商品快递运费不能为空'},
                    {type: 'number', message: '商品快递运费必须为数字值'}
                ],
                mailFreight: [
                    {required: true, message: '商品平邮运费不能为空'},
                    {type: 'number', message: '商品平邮运费必须为数字值'}
                ]
            }
        }
    },
    created() {
        this.$http.get(this.findAllGoodCategoryUrl).then((res) => {
            this.goodCategorys = res.data.data;
        });
    },
    watch: {
        '$route'(to, from) {
            this.active = 0;
            this.baseInfoDisplay = "display: block;";
            this.uploadPictureDisplay = "display: none;";
            this.addAttrDisplay = "display: none;";
            this.finishDisplay = "display: none;";
            this.nextCss = "";
            this.finishCss = "display: none;";
        }
    },
    computed: {},
    methods: {
        next() {
            if (this.active == 0) {
                this.saveGood('addForm');
            }
            if (this.active == 1) {
                this.uploadPictureDisplay = "display: none;";
                this.addAttrDisplay = "display: block;"
                this.nextCss = "display: none;";
                this.finishCss = "display: block;";
                this.active++;
            }
        },
        finish() {
            this.active++;
            this.uploadPictureDisplay = "display: none;";
            this.baseInfoDisplay = "display: none;";
            this.addAttrDisplay = "display: none;"
            this.finishDisplay = "display: block;"
            this.nextCss = "display: none;";
            this.finishCss = "display: none;";
            this.active++;
        },
        goBack() {
            this.finishCss = "display: none;";
            this.$router.push("/goodManager");
        },
        handleExceed(files, fileList) {
            this.$message.error('上传图片个数超过限制!最多可以上传12张图片。');
        },
        //保存商品别信息
        saveGood(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.get(this.saveUrl, {
                        params: {
                            name: this.addForm.name,
                            categoryId: this.addForm.categoryId,
                            price: this.addForm.price,
                            weight: this.addForm.weight,
                            emsFreight: this.addForm.emsFreight,
                            expressFreight: this.addForm.expressFreight,
                            mailFreight: this.addForm.mailFreight
                        }
                    }).then((res) => {
                        if (res.data.code == 0) {
                            this.uploadData.ownerId = res.data.data;
                            this.addBaseInfoSuccess = true;
                            this.$message.success('添加商品基本信息成功');
                            this.baseInfoDisplay = "display: none;";
                            this.uploadPictureDisplay = "display: block;";
                            this.active++;
                        }
                    });
                } else {
                    return false;
                }
            });
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
            this.$http.get(this.deleteImageUrl, {
                params: {
                    type: this.uploadData.type,
                    ownerId: this.uploadData.ownerId,
                    fileName: file.name
                }
            }).then((res) => {
                if (res.data.code == 0) {
                    this.$message.success('删除图片成功');
                } else {
                    this.$message.success('删除图片失败');
                }
            });
        },
        uploadError(err, file, fileList) {
            this.$message.error(JSON.parse(err.message).message);
            console.log(err, file, fileList);
        }
    }
}
