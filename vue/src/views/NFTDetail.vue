<template>

  <div class="product-page" style="padding: 20px;">
    <!-- 顶部店铺信息 -->
    <div class="store-info" style="display: flex; align-items: center; margin-bottom: 10px; margin-top: 20px; margin-left: 20px">
      <!-- 店铺图标 -->
<!--      <img src="@/assets/img/11.png" alt="店铺图标" style="width: 50px; height: 50px; border-radius: 50%; margin-right: 15px;" />-->
      <!-- 店铺信息 -->
      <div style="display: flex; flex-direction: column;">
        <span style="font-size: 30px;">{{ this.itemData.username }}</span>
        <div style="display: flex; align-items: center; margin-top: 10px;">
          <el-rate v-model="this.itemData.rating" disabled style="margin-right: 10px;"></el-rate>
          <span style="color: #f56c6c;">{{ this.itemData.rating }}</span>
          <span style="margin-left: 15px; font-size: 20px">资产编号：{{ this.itemData.tokenid }}</span>
        </div>
      </div>
    </div>

    <!-- 商品内容部分 -->
    <div class="product-details" style="display: flex; justify-content: space-between;">

      <!-- 商品图片展示区域 -->
      <div class="product-images" style="width: 45%; display: flex; flex-direction: column;">
<!--        &lt;!&ndash; 小图列表 &ndash;&gt;-->
<!--        <el-image-->
<!--            v-for="(image, index) in images"-->
<!--            :key="index"-->
<!--            :src="image"-->
<!--            style="margin-bottom: 10px; width: 80px; height: 80px; cursor: pointer;"-->
<!--            @click="mainImage = image"-->
<!--        ></el-image>-->
        <!-- 大图展示 -->
        <el-image
            :src="this.itemData.picture"
            style="width: auto; height: auto; margin-top: 20px; margin-left: 20px"
        ></el-image>
      </div>

      <!-- 商品信息区域 -->
      <div class="product-info" style="width: 50%; padding-left: 20px;">
        <!-- 商品标题 -->
        <h1 style="font-size: 35px; font-weight: bold;"> {{ this.itemData.name }} </h1>

        <!-- 价格和所有权重区域 -->
        <div style="margin-top: 20px;">
          <el-divider></el-divider>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <!-- 价格部分 -->
            <div style="display: flex; align-items: center;">
              <span style="font-size: 18px; font-weight: bold;">价格:&nbsp;</span>
              <span style="color: red; font-size: 24px; margin-left: 10px;">{{ this.itemData.price }}/1%</span>
              <span style="font-size: 18px; margin-left: 50px; font-weight: bold">所有权重:&nbsp;</span>
              <span style="color: #409EFF; font-size: 24px; margin-left: 10px;">{{ this.itemData.value }}%</span> <!-- 这里是所有权重数据 -->
            </div>
          </div>
        </div>
        <!-- 配送和库存信息 -->
        <p style= "font-size: 24px;">监管部门所在地: {{ this.itemData.supervision }}</p>
        <p style= "font-size: 24px;">数据清算机构: {{ this.itemData.authority }}</p>

        <!-- 描述文本 -->
        <p style="margin-top: 30px; font-size: 20px">
          <strong>{{ this.itemData.info }}</strong>
        </p>

        <!-- 标签分类 -->
        <div style="margin-top: 20px;">
          <el-tag style="margin-right: 10px" type="success">大数据分析</el-tag>
          <el-tag style="margin-right: 10px" type="success">行为分析</el-tag>
          <el-tag style="margin-right: 10px" type="success">用户画像</el-tag>
          <el-tag style="margin-right: 10px" type="success">金融监管</el-tag>
          <el-tag style="margin-right: 10px" type="success">数据挖掘</el-tag>
        </div>

<!--        &lt;!&ndash; 用户评分 &ndash;&gt;-->
<!--        <div style="margin-top: 20px;">-->
<!--          <span style="font-size: 16px;">用户评分</span>-->
<!--          <el-rate v-model="rating" disabled></el-rate>-->
<!--        </div>-->

        <div style="margin-top: 20px;">
          <span style="font-size: 28px; font-weight: bold; margin-right: 15px;">流转权重</span>
          <!-- Use a number input or slider for selecting weight -->
          <el-input-number v-model="transferWeight" :min="1" :max="this.itemData.value" ></el-input-number>
        </div>


        <!-- 购买操作按钮 -->
        <div style="margin-top: 20px; display: flex; align-items: center;">
          <el-button type="danger" size="large" style="width: 150px; margin-right: 10px;" @click="openDialog">立即流转</el-button>




          <el-button type="warning" size="large" style="width: 150px; margin-right: 10px;">加入购物车</el-button>
          <el-button type="primary" size="large"> <el-icon><Star /></el-icon>收藏 </el-button>
        </div>
      </div>

    </div>

    <!-- 登录提示信息 -->
    <div style="text-align: center; margin-top: 90px;">
      <el-button type="primary" size="large" @click="returnView">返回首页</el-button>
      <p style="margin-top: 10px;">查看更多SFT信息</p>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import {ElMessageBox} from "element-plus";
import router from "../router";

export default {
  name: "Detail",
  created() {
    // 取出当前点击的商品ID
    // console.log("当前查询的ID是"+this.$route.query.id);
    this.loadData(this.$route.query.tokenid);
    // 从session中取出登陆者的信息
    let str = sessionStorage.getItem("user") || "{}";
    // 解析str
    this.user = JSON.parse(str);
    // console.log("现在登录的人是"+this.user.username);
    this.images = [
      'https://img.zcool.cn/community/01e0cb616e256f110f03a8db5cd63c.jpg',  // 图像来源：站酷，主题为金融相关
      'https://pic3.zhimg.com/v2-7c01b5bcb5d160f7b799b9904cbedb19_r.jpg',  // 来源：知乎，展示金融数据分析
      'https://img.zcool.cn/community/01d3a4616e25a11103fa79212d546e.jpg'   // 来源：站酷，金融服务封面图片
    ];






    this.mainImage = this.images[0];  // 默认展示第一张图片
  },
  data(){
    return{
      popoverVisible: true, // 控制下拉框显示
      transferWeight: 0,
      totalprice: 0,
      dv: false,
      quantity: 1,
      rating: 4.5,
      rate: 4.8,
      images: [],
      mainImage: '',
      // 商品详情的HTML
      proUrl: "",
      // 购买者的信息
      user: {},
      // 商品的信息
      itemData:{
      },
      // 购买的数量
      num: 1,
      // 描述内容样式
      rowStyle: {
        'text-align': 'center',
        'height': '65px',
        'font-size': '15px',
        'background': 'rgba(226,231,253,0)',
      },
      // 描述标签样式
      labelStyle:{
        'text-align': 'center',
        'font-size': '15px',
        'background': '#E1F3D8',
      },
      // 价格标红
      rowStyle_price: {
        'text-align': 'center',
        'height': '65px',
        'font-size': '25px',
        'background': '#FDE2E2',
        'color': 'rgba(255,68,0)'
      },
    }
  },
  methods: {
    openDialog() {
      this.totalprice = this.transferWeight * this.itemData.price;
      this.$alert(
          `
  <div style="font-size: 16px; line-height: 1.8; color: #333;">
    <p>您正在流转以下数据集：</p>
    <p style="font-weight: bold; color: #FF5722; font-size: 18px;">${this.itemData.name}</p>
    <p>流转权重：<span style="color: #409EFF;">${this.transferWeight}%</span></p>
    <p>总价格：<span style="color: #67C23A; font-weight: bold;">¥${this.totalprice}</span></p>
    <p>请确认是否继续？</p>
  </div>
  `,
          '确认流转',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            dangerouslyUseHTMLString: true, // 启用 HTML 格式化内容
            type: 'warning',
          }
      )
          .then(() => {
            // 跳转到订单页面并带上参数
            router.push({
              path: '/trans',
              query: {
                tokenid: this.itemData.tokenid,
                transferWeight: this.transferWeight,
                totalprice: this.totalprice,
              },
            });
          })
          .catch(() => {
            // 用户取消流转
            console.log('流转取消');
          });

      // //    const accountAddress = '中国证券市场研究所';
   // //    const zcbh = '0062015234FS200765'; // 公钥文件名
   // //    const sjmc = '沪深股市每日交易数据'; // 公钥文件下载链接
   // //    const lzqz = '15'; // 私钥文件名
   // //
   //  this.$alert(
   // //        `<div style="font-family: Arial, sans-serif; line-height: 1.8; width: 600px; font-size: 18px; padding: 40px; border-radius: 10px; background-color: #f8f8ff; box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);">
   // //   <h3 style="margin-bottom: 20px; font-size: 22px; color: #444; font-weight: bold;">确认订单信息</h3>
   // //
   // //   <p style="margin-bottom: 20px; font-weight: bold; color: #333;">数据所有者:</p>
   // //   <p style="margin-bottom: 25px; background-color: #e6f7ff; padding: 12px; border-radius: 5px; word-break: break-all; border: 1px solid rgba(210, 230, 250, 0.6); color: #0050b3;">${accountAddress}</p>
   // //
   // //   <p style="margin-bottom: 20px; font-weight: bold; color: #333;">资产编号:</p>
   // //   <p style="margin-bottom: 25px; background-color: #e6f7ff; padding: 12px; border-radius: 5px; word-break: break-all; border: 1px solid rgba(210, 230, 250, 0.6); color: #0050b3;">${zcbh}</p>
   // //
   // //   <p style="margin-bottom: 20px; font-weight: bold; color: #333;">数据名称:</p>
   // //   <p style="margin-bottom: 25px; background-color: #e6f7ff; padding: 12px; border-radius: 5px; word-break: break-all; border: 1px solid rgba(210, 230, 250, 0.6); color: #0050b3;">${sjmc}</p>
   // //
   // //   <p style="margin-bottom: 20px; font-weight: bold; color: #333;">流转权重:</p>
   // //   <p style="margin-bottom: 25px; background-color: #e6f7ff; padding: 12px; border-radius: 5px; word-break: break-all; border: 1px solid rgba(210, 230, 250, 0.6); color: #0050b3;">${lzqz}</p>
   // //
   // //   <!-- 新增的上传私钥部分 -->
   // //   <p style="margin-bottom: 20px; font-weight: bold; color: #333;">上传私钥文件:</p>
   // //   <input type="file" style="display: block; margin-bottom: 25px; padding: 10px; border: 1px solid rgba(210, 230, 250, 0.6); background-color: #e6f7ff; border-radius: 5px;" />
   // //
   // //   <div style="margin-bottom: 30px;">
   // //     <span style="font-size: 18px; font-weight: bold; color: #333;">总价</span>
   // //     <span style="color: red; font-size: 28px; margin-left: 10px;">￥2985</span>
   // //   </div>
   // //
   // //   <div style="border-top: 1px solid #dcdcdc; padding-top: 20px; margin-top: 20px; text-align: center;">
   // //     <p style="font-weight: bold; margin-bottom: 15px; color: #333;">选择支付方式:</p>
   // //     <img src="https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2024%2F0316%2Fa3edfbfdj00saeeey002ed200u000frg00it009v.jpg&thumbnail=660x2147483647&quality=80&type=jpg" alt="微信支付" style="height: 40px; margin-right: 20px;" />
   // //     <img src="https://q3.itc.cn/q_70/images03/20240119/e6d0d3a05553429097179be1a6ad1aa5.jpeg" alt="支付宝" style="height: 40px;" />
   // //   </div>
   // // </div>`,
   //         '确认订单信息',
   //        {
   //          confirmButtonText: '确认支付',
   //          dangerouslyUseHTMLString: true,
   //          callback: action => {
   //            this.$message({
   //              type: 'info',
   //              message: `action: ${action}`
   //            });
   //          }
   //        }
   // );


    },
    returnView() {
      this.$router.push("/web/nftview")
    },
    loadData:function (tokenid){
      console.log(tokenid)
      // 加载商品数据
      request.get('/nftview/one',{
        params:{
          tokenid: tokenid,
        }
      }).then(res => {
        console.log(res);
        this.itemData = res.data;
        document.title = "NFT详情";
      });
      // 加载详情数据
      // request.get("/itemDetail",{
      //   params: {
      //     tokenid: tokenid,
      //   }
      // }).then(res => {
      //   this.proUrl = res.data.itemDetail;
      // });
    },
    buy() {
      request.get("/order/buy",{
        params:{
          itemId: this.itemData.id,
          userId: this.user.id,
          count: this.num
        }
      }).then(res => {
        this.$message.success("即将进入支付页面~~~");
        // 直接通过返回的链接打开新窗口
        alert("即将进入支付页面，请注意支付环境安全")
        window.open(res.data);
      })
    },
    handleChange(){
    },
    addCart(){
      this.$confirm('是否确定加入购物车?', '==================', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).
      then(() => {
        request.get('/order/add',{
          params: {
            itemId: this.itemData.id,
            userId: this.user.id,
            count: this.num
          }
        }).
        then(res => {
          this.$message.success("加入购物车成功~");
        });
      }).
      catch(() => {
        this.$message({
          type: 'error',
          message: '取消加入购物车'
        });
      });
    }
  }
}
</script>

<style scoped>
.product-page{
  padding: 20px;
  position: relative;
  width: 100%;
  height: 100%;
  background: #ffffff;
  background-image: url("../assets/img/10.png")!important;

  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
.recommend{
  float: right;
  background-color: rgb(255, 255, 255);
}
.my-button{
  height: 70px;
  width: 200px;
  font-size: 25px;
  border-radius: 4px;
  color: white;
  background-color: rgb(0, 51, 255);
  margin: 200px 200px;
}

.price-info {
  text-align: center;
  background-color: #f3f3f3;
  height: auto;
  margin: 50px;

  h2 {
    font-size: 20px;
    padding-top: 38px;
    margin-bottom: 30px;
    line-height: 100px;
  }

  .attributes-list {
    width: 749px;
    display: block;
    clear: both;
    margin: 20px auto;
    min-height: 50px;
    text-align: left;

    li {
      display: inline;
      float: left;
      width: 206px;
      height: 24px;
      margin-right: 20px;
      overflow: hidden;
      text-indent: 5px;
      line-height: 24px;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
  }
}

.nftdetail-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background: #ffffff;
  background-image: url("../assets/img/5.png")!important;

  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

</style>