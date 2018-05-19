<template>
    <div class="login-wrap">
        <div class="ms-title">体验酒柜后台管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" prefix-icon="icon-zhanghu" placeholder="请输入用户名"></el-input>
                    <!--<el-input v-model="ruleForm.username" prefix-icon="ye-icon-account" placeholder="请输入用户名"></el-input>-->
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="ruleForm.password" type="password" prefix-icon="icon-mima" placeholder="请输入密码" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                ruleForm: {
                    username: 'yangyijun',
                    password: '123456'
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$http.post('/login', {
                            userName: this.ruleForm.username,
                            password: this.ruleForm.password
                        }).then((res) => {
                            if (res.data.code == 0) {
                                // this.$http.defaults.params = {"JSESSIONID": res.data.data.jsessionid}
                                // 在实例已创建后修改默认值
                                // localStorage.setItem('JSESSIONID', res.data.data.jsessionid);
                                this.$message({
                                    message: '登录成功！',
                                    type: 'success'
                                });
                                console.log(res)
                                localStorage.setItem('ms_username', res.data.data.name);
                                this.$router.push('/');
                            } else {
                                this.$message({
                                    message: '用户名或者密码错误！',
                                    type: 'error'
                                });
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
    }

    /*.icon-mima {
        background-color: rgba(0, 0, 0, 0.1);
    }*/

    .ms-title {
        position: absolute;
        top: 50%;
        width: 100%;
        margin-top: -230px;
        text-align: center;
        font-size: 30px;
        color: #fff;
    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 300px;
        height: 160px;
        margin: -150px 0 0 -190px;
        padding: 40px;
        border-radius: 5px;
        background: #fff;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 100%;
        height: 36px;
    }
</style>
