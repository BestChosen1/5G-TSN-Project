<template>
  <div style="height: 100vh;flex-direction: column; display: flex; align-items: center; justify-content: center; " class="register-wrap">

    <div style="height: 100vh; width: 50% ;display: flex; align-items: center; justify-content: center; ">
      <div style="display: flex; flex-direction: column; align-items:center;">

        <div style="flex: 1">
          <img src="@/assets/img/12.png" alt="" style="width: 1500px;height: 300px">
        </div>
        <div style="height: 20px">
        </div>

        <div style="display: flex; background-color: rgba(255,255,255,0.49); width: 600px; height: 400px; border-radius: 5px; overflow: hidden;">

      <div style="flex: 1; display: flex; align-items: center; justify-content: center">
        <el-form style=" width: 400px; font-size: 20px" :model="form"  :rules="rules" ref="form">
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px"> </div>
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 15px">用户注册</div>
          <el-form-item prop="username">
            <el-input style="font-size: 20px" prefix-icon="el-icon-user" size="big" placeholder="请输入账号" v-model="form.username">
              <template #prefix>
                <el-icon class="el-input__icon"><User/></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input style="font-size: 20px" prefix-icon="el-icon-lock" size="big" show-password placeholder="请输入密码" v-model="form.password">
              <template #prefix>
                <el-icon class="el-input__icon"><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="confirmPass">
            <el-input style="font-size: 20px" prefix-icon="el-icon-lock" size="big" show-password placeholder="请确认密码" v-model="form.confirm">
              <template #prefix>
                <el-icon class="el-input__icon"><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>


          <el-form-item>
            <div style="display: flex">
              <el-input  v-model="form.validCode" style="width: 45%; font-size: 20px"  size="big" placeholder="请输入验证码">
                <template #prefix>
                  <el-icon class="el-input__icon"><Check /></el-icon>
                </template>
              </el-input>
              <ValidCode @input="createValidCode" style="width: 50%"/>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="success" style="width: 100%; font-size: 20px" @click="register">注 册</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">已经有账号了？请 <span style="color: #6e77f2; cursor: pointer" @click="$router.push('/login')">登录</span></div>
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
import {ElMessage, ElMessageBox} from "element-plus";
import ValidCode from "../components/Validate";
import {Check, Lock, User} from "@element-plus/icons";
export default {
  name: "Login",
  components:{
    User,
    Lock,
    Check,
    ValidCode,
  },
  data(){
    return{
      publicKeyUrl: "",
      privateKeyUrl: "",
      form:{},
      validCode: '',
      username: '',
      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
          {
            min: 2,
            max: 13,
            message: '长度要求为2到13位',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          }
        ],
      confirm:[
        {
          required:true,
          message:"请确认密码",
          trigger:"blur"
        }
      ],
        // authorize:[
        //   {
        //     required:true,
        //     message:"请输入注册码",
        //     trigger:"blur"
        //   }
        // ],
      }
    }
    },

  methods:{
    createValidCode(data){
      this.validCode =data
    },
    handleRegister(data){
      this.username = data.username
      this.publicKeyUrl = `/download/${this.username}_public.pub.pem`
      this.privateKeyUrl = `/download/${this.username}_public.pub.pem`
      console.log(this.privateKeyUrl)
      console.log(this.publicKeyUrl)
      console.log(this.username)
      // 弹出美观的下载提示框
     this.$alert(
          `
            <div style="text-align: left;">
              <p>注册成功！请点击以下链接下载您的公私钥文件：</p>
              <p>
                <a href="${this.publicKeyUrl}" download style="color: #409EFF;">下载公钥文件 (${this.username}_public.pub.pem)</a>
              </p>
              <p>
                <a href="${this.privateKeyUrl}" download style="color: #409EFF;">下载私钥文件 (${this.username}_private.pem)</a>
              </p>
            </div>
            `,
          "下载密钥文件",
          {
            dangerouslyUseHTMLString: true, // 支持 HTML 字符串
            confirmButtonText: "知道了",
          }
      );
      this.$router.push("/login")
      // const accountAddress = '0x7cd17e596f7709b493a1203955ef9ec4ff569382';
      // const publicKeyFileName = '2023510379_public.pub.pem'; // 公钥文件名
      // const publicKeyFileUrl = '公钥文件下载链接'; // 公钥文件下载链接
      // const privateKeyFileName = '2023510379_private.pem'; // 私钥文件名
      // const privateKeyFileUrl = '私钥文件下载链接'; // 私钥文件下载链接


   //    this.$alert(
   //        `<div style="font-family: Arial, sans-serif; line-height: 1.6; width: 400px; font-size: 18px; padding: 40px; border-radius: 10px; background-color: #fff; box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);">
   //   <h3 style="margin-bottom: 30px; font-size: 18px; color: #333;">区块链账户生成成功，点击链接即可下载</h3>
   //   <p style="margin-bottom: 20px; font-weight: bold;">账户地址:</p>
   //   <p style="margin-bottom: 30px; background-color: #f0f0f5; padding: 20px; border-radius: 6px; word-break: break-all; border: 1px solid #dcdcdc;">${accountAddress}</p>
   //   <p style="margin-bottom: 20px; font-weight: bold;">公钥文件:</p>
   //   <p style="margin: 20px 0; word-break: break-all;"><a href="${publicKeyFileUrl}" download style="color: #007BFF; text-decoration: underline;">${publicKeyFileName}</a></p>
   //   <p style="margin-bottom: 20px; font-weight: bold;">私钥文件:</p>
   //   <p style="margin: 20px 0; word-break: break-all;"><a href="${privateKeyFileUrl}" download style="color: #007BFF; text-decoration: underline;">${privateKeyFileName}</a></p>
   // </div>`,
   //        '区块链账户生成',
   //        {
   //          confirmButtonText: '确定',
   //          dangerouslyUseHTMLString: true,
   //          callback: action => {
   //            this.$message({
   //              type: 'info',
   //              message: `action: ${action}`
   //            });
   //          }
   //        }
   //    );






    },
    register(){
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
          if(this.form.password != this.form.confirm)
          {
            ElMessage.error("两次密码输入不一致")
            return
          }
          // if(this.form.role == 1 && this.form.authorize != "123456")
          // {
          //   ElMessage.error("请输入正确的注册码")
          //   return
          // }
          console.log(this.form)
          request.post("user/register",this.form).then(res=>{
            if(res.code == 0)
            {
              console.log(res)
              this.handleRegister(res.data)
            }
            else {ElMessage.error(res.status)}
          })
          // this.handleRegister()
        }
      })

    }
  }

  }

</script>


<style scoped>
.register-wrap {
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