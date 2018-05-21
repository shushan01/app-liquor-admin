export default {
    data() {
        return {
            findAllGoodCategoryUrl: '/goodCategory/findAll',
            saveUrl: '/good/save',
            uploadUrl: 'http://localhost:8080/good/uploadPicture',
            goodCategorys: [],
            fileList: [],
            uploadData: {
                goodCode: ''
            },
            active: 0,
            baseInfoDisplay: "display: block;",
            uploadPictureDisplay: "display: none;",
            addAttrDisplay: "display: none;",
            finishDisplay: "display: none;",
            // prevCss: "display: none;",
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
        // prev() {
        //     this.active--;
        //     if (this.active == 0) {
        //         this.prevCss = "display: none;";
        //     }
        //     if (this.active == 1) {
        //         this.prevCss = "display: block;";
        //     }
        //     if (this.active == 2) {
        //         this.prevCss = "display: block;";
        //     }
        // },
        next() {
            if (this.active == 0) {
                this.saveGood('addForm');
                this.baseInfoDisplay = "display: none;";
                this.uploadPictureDisplay = "display: block;";
                // this.prevCss = "display: block;";
            }
            if (this.active == 1) {
                this.uploadPictureDisplay = "display: none;";
                this.addAttrDisplay = "display: block;"
                this.nextCss = "display: none;";
                this.finishCss = "display: block;";
            }
            this.active++;
        }
        ,
        finish() {
            this.active++;
            this.uploadPictureDisplay = "display: none;";
            this.baseInfoDisplay = "display: none;";
            this.addAttrDisplay = "display: none;"
            this.finishDisplay = "display: block;"
            // this.prevCss = "display: none;";
            this.nextCss = "display: none;";
            this.finishCss = "display: none;";
            this.active++;
        }
        ,
        goBack() {
            this.finishCss = "display: none;";
            this.$router.push("/goodManager");
        }
        ,
        handleExceed(files, fileList) {
            this.$message.error('上传图片个数超过限制!最多可以上传12张图片。');
        },
        // handleSuccess(response, file, fileList) {
        //     console.log(response, file, fileList)
        //     this.fileList.push("1111.jpg")
        //     // this.fileList = fileList;
        // }
        // ,
        //保存商品别信息
        saveGood(formName) {
            // this.$refs[formName].validate((valid) => {
            //     if (valid) {
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
                if (res.status == 200) {
                    this.uploadData.goodCode = '';
                    this.$message.success('添加商品基本信息成功');
                }
            });
            //     } else {
            //         return false;
            //     }
            // });
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        uploadError(err, file, fileList) {
            this.$message.error(JSON.parse(err.message).message);
            console.log(err, file, fileList);
        }
    }
}
