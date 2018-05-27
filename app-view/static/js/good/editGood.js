export default {
    data() {
        return {
            findAllGoodCategoryUrl: '/goodCategory/findAll',
            saveUrl: '/good/update',
            detailUrl: '/good/detail',
            uploadUrl: '',
            deleteImageUrl: '/image/delete',
            goodCategorys: [],
            carouselPageUploadData: {
                ownerId: -1,
                type: 'good',
                position: 1
            },
            listPageUploadData: {
                ownerId: -1,
                type: 'good',
                position: '2'
            },
            detailPageUploadData: {
                ownerId: -1,
                type: 'good',
                position: '3'
            },
            carouselFileList: [],
            listFileList: [],
            detailFileList: [],
            activeNames: ['1', '2', '3', '4'],
            dialogImageUrl: '',
            dialogVisible: false,
            active: 0,
            baseInfoDisplay: "display: block;",
            uploadPictureDisplay: "display: none;",
            // addAttrDisplay: "display: none;",
            finishDisplay: "display: none;",
            nextCss: "",
            finishCss: "display: none;",
            editForm: {
                id: '',
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
                    {max: 50, message: '商品名称不超过50个字符', trigger: 'blur'}
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
        this.editForm.id = this.$route.params.goodId;
        this.getData();
        this.$http.get(this.findAllGoodCategoryUrl).then((res) => {
            this.goodCategorys = res.data.data;
        });
        this.uploadUrl = localStorage.getItem("baseUrl") + "/image/upload";
    },
    watch: {
        '$route'(to, from) {
            this.active = 0;
            this.baseInfoDisplay = "display: block;";
            this.uploadPictureDisplay = "display: none;";
            // this.addAttrDisplay = "display: none;";
            this.finishDisplay = "display: none;";
            this.nextCss = "";
            this.finishCss = "display: none;";
            this.editForm.id =''
            this.editForm.id = this.$route.params.goodId;
            this.getData();
        }
    },
    computed: {},
    methods: {
        //加载表格数据
        getData() {
            this.$http.get(this.detailUrl, {
                params: {
                    id: this.editForm.id
                }
            }).then((res) => {
                console.log(res.data)
                this.carouselFileList = res.data.data.carouselPictures;
                this.listFileList = res.data.data.goodListPictures;
                this.detailFileList = res.data.data.goodDetailPictures;
                this.editForm = res.data.data.good;
            });
        },
        next() {
            if (this.active == 0) {
                this.saveGood('editForm');
            }
            if (this.active == 1) {
                this.uploadPictureDisplay = "display: none;";
                // this.addAttrDisplay = "display: block;"
                this.nextCss = "display: none;";
                this.finishCss = "display: block;";
                this.active++;
            }
        },
        finish() {
            this.active++;
            this.uploadPictureDisplay = "display: none;";
            this.baseInfoDisplay = "display: none;";
            // this.addAttrDisplay = "display: none;"
            this.finishDisplay = "display: block;"
            this.nextCss = "display: none;";
            this.finishCss = "display: none;";
            this.active++;
        },
        goBack() {
            this.finishCss = "display: none;";
            this.$router.push("/goodManager");
        },
        carouselHandleExceed(files, fileList) {
            this.$message.error('上传图片个数超过限制!最多可以上传5张图片。');
        },
        listPageHandleExceed(files, fileList) {
            this.$message.error('上传图片个数超过限制!最多可以上传3张图片。');
        },
        detailPageHandleExceed(files, fileList) {
            this.$message.error('上传图片个数超过限制!最多可以上传12张图片。');
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        //保存商品别信息
        saveGood(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.get(this.saveUrl, {
                        params: {
                            id: this.editForm.id,
                            name: this.editForm.name,
                            categoryId: this.editForm.categoryId,
                            price: this.editForm.price,
                            weight: this.editForm.weight,
                            emsFreight: this.editForm.emsFreight,
                            expressFreight: this.editForm.expressFreight,
                            mailFreight: this.editForm.mailFreight
                        }
                    }).then((res) => {
                        if (res.data.code == 0) {
                            this.carouselPageUploadData.ownerId = res.data.data;
                            this.listPageUploadData.ownerId = res.data.data;
                            this.detailPageUploadData.ownerId = res.data.data;
                            this.addBaseInfoSuccess = true;
                            this.$message.success('修改商品基本信息成功');
                            this.baseInfoDisplay = "display: none;";
                            this.uploadPictureDisplay = "display: block;";
                            this.finishCss = "display: block;"
                            this.nextCss = "display: none;"
                            this.active++;
                        }
                    });
                } else {
                    return false;
                }
            });
        },
        carouselHandleRemove(file, fileList) {
            this.$http.get(this.deleteImageUrl, {
                params: {
                    type: this.carouselPageUploadData.type,
                    ownerId: this.carouselPageUploadData.ownerId,
                    fileName: file.name,
                    position: 1
                }
            }).then((res) => {
                if (res.data.code == 0) {
                    this.$message.success('删除图片成功');
                } else {
                    this.$message.success('删除图片失败');
                }
            });
        },
        listPageHandleRemove(file, fileList) {
            this.$http.get(this.deleteImageUrl, {
                params: {
                    type: this.listPageUploadData.type,
                    ownerId: this.listPageUploadData.ownerId,
                    fileName: file.name,
                    position: 2
                }
            }).then((res) => {
                if (res.data.code == 0) {
                    this.$message.success('删除图片成功');
                } else {
                    this.$message.success('删除图片失败');
                }
            });
        },
        detailPageHandleRemove(file, fileList) {
            this.$http.get(this.deleteImageUrl, {
                params: {
                    type: this.detailPageUploadData.type,
                    ownerId: this.detailPageUploadData.ownerId,
                    fileName: file.name,
                    position: 3
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
        }
    }
}
