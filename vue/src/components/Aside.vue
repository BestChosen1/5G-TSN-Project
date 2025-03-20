<template>
<div>


  <el-menu
      style="width: 200px;min-height: calc(100vh - 50px);"
      :default-active="path"
      class="el-menu-vertical-demo"
      router
      background-color="#CAB230AA"
      text-color="#000"
      
  >
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Monitor /></el-icon>
      <span>首页</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;" >
      <el-icon icon="el-icon-s-management"><CreditCard /></el-icon>
      <span>用户监管</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Avatar /></el-icon>
      <span>机构审查</span>
    </el-menu-item>
    <el-menu-item index="/cmt" style="font-size: 18px; padding: 40px;">
      <el-icon><Edit /></el-icon>
      <span>数据监管</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Tickets /></el-icon>
      <span>交易监管</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><DocumentAdd /></el-icon>
      <span>流水监管</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Key /></el-icon>
      <span>风控管理</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Box /></el-icon>
      <span>质量评估</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;" >
      <el-icon icon="el-icon-s-management"><TurnOff /></el-icon>
      <span>纠纷处理</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon ><Aim /></el-icon>
      <span  class="icon-r-setting">数据统计</span>
    </el-menu-item>
    <el-menu-item index="/dashboard" style="font-size: 18px; padding: 40px;">
      <el-icon><Lock /></el-icon>
      <span>系统维护</span>
    </el-menu-item>
<!--    <el-sub-menu index="2" text-color="#7C9CC389">-->
<!--      <template #title>-->
<!--        <el-icon><CreditCard /></el-icon>-->
<!--        <span>个人信息</span>-->
<!--      </template>-->
<!--      <el-menu-item index="/person" style="font-color: rgba(124,156,195,0.54)">-->
<!--        <el-icon><Edit /></el-icon>-->
<!--        <span>修改个人信息</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/password">-->
<!--        <el-icon><Key /></el-icon>-->
<!--        <span>修改密码</span>-->
<!--      </el-menu-item>-->
<!--    </el-sub-menu>-->

<!--    <el-menu-item index="/user" v-if="user.role == 1">-->
<!--      <el-icon><User /></el-icon>-->
<!--      <span>团干管理</span>-->
<!--    </el-menu-item>-->

<!--    <el-sub-menu index="2" text-color="#7C9CC389">-->
<!--      <template #title>-->
<!--        <el-icon><CreditCard /></el-icon>-->
<!--        <span>数据资产管理</span>-->
<!--      </template>-->
<!--      <el-menu-item index="/cmt" style="font-color: rgba(124,156,195,0.54)">-->
<!--        <el-icon><Edit /></el-icon>-->
<!--        <span>资产概览</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/cmt">-->
<!--        <el-icon><Key /></el-icon>-->
<!--        <span>资产管理</span>-->
<!--      </el-menu-item>-->
<!--    </el-sub-menu>-->
<!--    <el-sub-menu index="2" text-color="#7C9CC389">-->
<!--      <template #title>-->
<!--        <el-icon><CreditCard /></el-icon>-->
<!--        <span>交易管理</span>-->
<!--      </template>-->
<!--      <el-menu-item index="/person" style="font-color: rgba(124,156,195,0.54)">-->
<!--        <el-icon><Edit /></el-icon>-->
<!--        <span>交易记录</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/password">-->
<!--        <el-icon><Key /></el-icon>-->
<!--        <span>支付管理</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/password">-->
<!--        <el-icon><Key /></el-icon>-->
<!--        <span>订单管理</span>-->
<!--      </el-menu-item>-->
<!--    </el-sub-menu>-->

<!--    <el-menu-item index="/archive" >-->
<!--      <el-icon><Tickets /></el-icon>-->
<!--      <span>档案管理</span>-->
<!--    </el-menu-item>-->

<!--    <el-menu-item index="/newmem">-->
<!--      <el-icon><DocumentAdd /></el-icon>-->
<!--      <span>发展团员</span>-->
<!--    </el-menu-item>-->

  </el-menu>

</div>
</template>

<script>


import {Aim, Apple, Avatar, Box, Lock, Monitor, TurnOff} from "@element-plus/icons";

export default {
  name: "Aside",
  components:{TurnOff, Box, Avatar, Apple, Lock, Aim, Monitor},
  created(){
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
  },
  data(){
    return {
      user:{},
      path: this.$route.path

    }
  }
}
</script>

<style scoped>
.icon {
  width: 30px;
  height: 30px;
  padding-top: 5px;
  padding-right: 10px;
}
.el-menu-item.is-active {
  color: #ffffff;
}
.el-menu-item.is-active {
  background-color: rgba(198, 176, 27, 0.82);
}


</style>