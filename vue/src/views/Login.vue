<template>
  <div style="height: 100vh;flex-direction: column; display: flex; align-items: center; justify-content: center; " class="login-wrap">

    <div style="height: 100vh; width: 50% ;display: flex; align-items: center; justify-content: center; ">
      <div style="display: flex; flex-direction: column; align-items:center;">

        <div style="flex: 1">
          <img src="@/assets/img/12.png" alt="" style="width: 1500px;height: 300px">
        </div>
        <div style="height: 20px">
        </div>
        <div style="display: flex; background-color: rgba(255,255,255,0.49); width: 600px; height: 400px; border-radius: 5px; overflow: hidden;">

          <div style="flex: 1; display: flex; align-items: center; justify-content: center; margin-top: -10px">
            <el-form style=" width: 500px; font-size: 20px" :model="form"  :rules="rules" ref="form">
              <div style="font-size: 500px; font-weight: bold; text-align: center; margin-bottom: 10px"> </div>
              <el-form-item prop="username">
                <el-input style="font-size: 20px" v-model="form.username" size="big" placeholder="请输入账号"  clearable>
                  <template #prefix>
                    <el-icon class="el-input__icon"><User /></el-icon>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item prop="password">
                <el-input style="font-size: 20px" v-model="form.password"   size="big"  placeholder="请输入密码" clearable show-password>
                  <template #prefix>
                    <el-icon class="el-input__icon" ><Lock /></el-icon>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item prop="code">
                <div style="display: flex">
                  <el-input placeholder="请输入验证码" prefix-icon="el-icon-circle-check" size="big" style="flex: 1; font-size: 20px" v-model="form.validCode">
                    <template #prefix>
                      <el-icon class="el-input__icon" ><Check/></el-icon>
                    </template>
                  </el-input>
                  <div style="font-size: 20px;  flex: 1; height: 36px;margin-left: 13px">

                    <ValidCode @input="createValidCode" style="width: 50%"/>
                  </div>
                </div>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" style="width: 100%; font-size: 20px" @click="login">登 录</el-button>
              </el-form-item>

              <div style="display: flex">
                <div style="flex: 1">还没有账号？请 <span style="color: #0f9876; cursor: pointer" @click="$router.push('/register')">注册</span></div>
                <div style="flex: 1; text-align: right"><span style="color: #0f9876; cursor: pointer" @click="handleForgetPass">忘记密码</span></div>
              </div>
              <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px"> </div>
            </el-form>
          </div>
        </div>




      </div>
    </div>
  </div>

</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";
import ValidCode from "../components/Validate";
import {Lock, User} from "@element-plus/icons";



export default {
  name: "Login",
  components:{
    User,
    Lock,
    ValidCode
  },
  data() {
    return {
      validCode: '',//通过valicode获取的验证码
      form: {},
      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          }
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          }
        ]

      },
      dialogVisible: false,  // 控制弹框显示与隐藏
      fileList: []           // 保存文件列表

    }
  },
  methods: {
    createValidCode(data){
      this.validCode = data
    },
    handleForgetPass(){
      // ElMessage.error("请联系校团委管理员")
      this.$alert('请联系系统管理员', '忘记密码', {
        confirmButtonText: '确定',
      });

    },
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.form.validCode) {
            ElMessage.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            ElMessage.error("验证码错误")
            return
          }

          request.post("user/login", this.form).then(res => {
            if (res.code == 0) {
              ElMessage.success("登录成功")
              sessionStorage.setItem("user",JSON.stringify(res.data))//缓存用户信息

              this.$router.push("/web/nftview")
            } else {
              ElMessage.error(res.msg)
            }
          })
        }
      })

    }
  }
}

</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background: #ffffff;
  background-image: url("../assets/img/10.png")!important;

  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>