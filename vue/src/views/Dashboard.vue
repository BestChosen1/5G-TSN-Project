<template>
  <div class="board-wrap">
    <div class="flex-container">
    <el-row :gutter="20" class="align-bottom">
      <el-col :span="6" v-for="item in cards" :key="item.title">
        <el-card class="box-card">
          <div slot="header" class="clearfix">{{ item.title }}</div>
          <div class="text item">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="item.icon" style="width: 100px"></use>
            </svg>
            <span class="text">{{ item.data }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
      </div>
<!--    <div id="myTimer" style="margin-left: 15px;font-weight: 550;"></div>-->
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<!--    <div id="main" style="margin-left: 5px"></div>-->
  </div>
</template>

<script>
import * as echarts from 'echarts'
import {ElMessage} from "element-plus";
import request from "../utils/request";

export default {
  data() {
    return {
      cards: [
        { title: '基层团委', data: 0, icon: '#iconlend-record-pro' },
        { title: '档案数量', data: 0, icon: '#iconbook-pro'   },
        { title: '团干账户', data: 0, icon: '#iconpopulation' },
        { title: '总访问数', data: 0, icon:  '#iconvisit'}
      ],
      data:{}
    }
  },
  created() {

  },
  mounted() {
    // this.circleTimer()

    request.get("/dashboard").then(res=>{
      if(res.code == 0)
      {

        this.cards[0].data = res.data.cmtCount
        this.cards[1].data = res.data.archiveCount
        this.cards[2].data = res.data.userCount
        this.cards[3].data = res.data.visitCount

      }
      else
      {
        ElMessage.error(res.msg)
      }



    })
  },
  methods: {
    circleTimer() {
      this.getTimer()
      setInterval(() => {
        this.getTimer()
      }, 1000)
    },
    getTimer() {
      var d = new Date()
      var t = d.toLocaleString()
      document.getElementById('myTimer').innerHTML = t
    }
  }
}
</script>

<style scoped>
.align-bottom {

  margin-top: auto;
}
.flex-container {
  display: flex;
  flex-direction: column; /* 垂直布局 */
  justify-content: space-between; /* 内容在垂直方向上均匀分布，但最后一个元素会被推到容器底部 */
  height: 100%; /* 或者你需要的任何高度 */
}
.board-wrap {
  position: relative;
  width: 100%;

  background: #ffffff;
  background-image: url("../assets/img/5.png")!important;

  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
.box-card {
   width: 80%;
  margin-bottom: 25px;
  margin-left: 10px;
  background-color: rgba(255, 255, 255, 0.21);
}

.clearfix {
  text-align: center;
  font-size: 15px;

}

.text {
  text-align: center;
  font-size: 24px;
  font-weight: 700;
  vertical-align: super;
}

#main {
  width: 100%;
  height: 450px;
  margin-top: 20px;
}

.icon {
  width: 50px;
  height: 50px;
  padding-top: 5px;
  padding-right: 10px;
}
</style>