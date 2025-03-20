<template>

  <!-- 头部 -->
  <el-header style="height: 80px">

    <div class="head-bar">
      <div class="header-ico">
        <!--      <i class="el-icon-s-home"></i>-->
        <img src="@/assets/img/12.png" style="width: 400px;
    height: 60px;
    margin-left: -20px;
    margin-top: 10px;">
      </div>
<!--            <div class="logo">-->
<!--        &lt;!&ndash;      <i class="el-icon-s-home"></i>&ndash;&gt;-->
<!--        <img src="@/assets/img/2.png" style="width: 200px;-->
<!--    height: 50px;-->
<!--    margin-left: 20px;-->
<!--    margin-top: 10px;">-->
<!--      </div>-->

<!--      <div class="logo">团务管理系统</div>-->
      <div class="head-right">
        <div class="head-user-con">
          <span class="el-dropdown-link" @click="goToFirst">  <span><b>
          <span class="name"> 首页 </span>
            <!--             <span class="name" v-if="user.role==1"> 团委管理员：</span>-->
            <!--          <span class="name" v-if="user.role==2"> 基层团干：</span>-->
          </b>&nbsp;&nbsp;</span>
          <i class="el-icon-caret-bottom"></i>
          </span>

          <span class="el-dropdown-link">  <span><b>
          <span class="name"> 我的 </span>
            <!--             <span class="name" v-if="user.role==1"> 团委管理员：</span>-->
            <!--          <span class="name" v-if="user.role==2"> 基层团干：</span>-->
          </b>&nbsp;&nbsp;</span>
          <i class="el-icon-caret-bottom"></i>
          </span>

          <span class="el-dropdown-link">  <span><b>
          <span class="name"> 关注 </span>
            <!--             <span class="name" v-if="user.role==1"> 团委管理员：</span>-->
            <!--          <span class="name" v-if="user.role==2"> 基层团干：</span>-->
          </b>&nbsp;&nbsp;</span>
          <i class="el-icon-caret-bottom"></i>
          </span>

          <span class="el-dropdown-link" @click="goToAdminPage">  <span><b>
          <span class="name"> 管理员中心 </span>
          <!--             <span class="name" v-if="user.role==1"> 团委管理员：</span>-->
          <!--          <span class="name" v-if="user.role==2"> 基层团干：</span>-->
          </b>&nbsp;&nbsp;</span>
          <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown @command="handleCommand" class="user-name" trigger="click">
          <span class="el-dropdown-link">

           <span><b>
              <span class="name"> 欢迎你！ </span>
<!--             <span class="name" v-if="user.role==1"> 团委管理员：</span>-->
<!--          <span class="name" v-if="user.role==2"> 基层团干：</span>-->
        </b>&nbsp;&nbsp;{{user.realName}}</span>
            <i class="el-icon-caret-bottom"></i>
          </span>
            <template #dropdown>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
            </template>
          </el-dropdown>


        </div>
      </div>
    </div>

  </el-header>




</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Header",
  props: ['user'],
  data(){
    return{
      user:[],
      imgUrl:require("../assets/icon/login.png")
    }
  },
  created(){
    let userStr = sessionStorage.getItem("user")||"{}"
    this.user = JSON.parse(userStr)
  },
  methods:{
    goToFirst() {
      this.$router.push({ path: '/web/nftview' });
    },
    goToAdminPage(){
      this.$router.push({ path: '/cmt' });
    },
    exit(){
      sessionStorage.removeItem("user")
      this.$router.push("/login")
      ElMessage.success("退出系统成功")
    },
    handleCommand(command) {
      if (command === "logout") {
        this.$confirm("此操作将退出登录, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
            .then(() => {
              sessionStorage.removeItem("user")
              this.$message({
                type: "success",
                message: "退出登录成功!",
              });
              this.$router.push("login");
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消",
              });
            });

      }
    },
  }

}
</script>

<style scoped>
.icon {
  width: 40px;
  height: 40px;
  padding-top: 5px;
  padding-right: 10px;
}
.title{
  cursor: pointer;
}
.el-header {
  background-color: rgba(202, 178, 48, 0.67);
  display: flex;
  justify-content: space-between;
  align-items: center;
  .words {
    text-align: center;
    span {
      color: black;
    }
  }

//border-bottom: 1px solid lightgrey;
}
.el-container{
  height: 100%;
}
.el-aside{
  background-color:#353744;
  border-right: 1px solid #000000;
}
.el-menu{
  border: 0;
}
.head-bar {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 18px;
  color: #000000;

}

.header-ico {
  float: left;
  padding: 0 21px;
  line-height: 70px;
}

.head-bar .logo {
  float: left;
  width: 250px;
  line-height: 70px;
  margin-left: -25px;
}

.head-right {
  float: right;
  padding-right: 50px;
}

.head-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}

.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}

.btn-bell .el-icon-bell {
  color: #000000;
}

.user-name {
  margin-left: 10px;
}

.user-avatar {
  margin-left: 20px;
}

.user-avatar img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.el-dropdown-link {
  color: #000000;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}
</style>