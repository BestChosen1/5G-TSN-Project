<template>
  <div class="cloud-services">
    <!-- CNKI Section (Login Section, centered at the top) -->
    <el-row justify="center" class="login-section">
      <el-col :span="12">
        <el-card class="cnki-card">
<!--          <div class="header">-->
<!--            <img src="https://www.cnki.net/images/cnki_logo.png" alt="CNKI Logo" class="cnki-logo">-->
<!--            <el-button type="primary" class="float-right">个人登录</el-button>-->
<!--          </div>-->
          <div class="search-bar">
            <!-- Dropdown search bar -->
            <el-select style="box-sizing:unset" v-model="selectedCategory" placeholder="选择分类" class="search-select">
              <el-option label="用户数据" value="用户数据"></el-option>
              <el-option label="交易数据" value="交易数据"></el-option>
              <el-option label="文本数据" value="文本数据"></el-option>
              <el-option label="其他数据" value="其他数据"></el-option>
<!--              <el-option label="专利" value="patents"></el-option>-->
            </el-select>
            <el-input placeholder="输入关键词"  prefix-icon="el-icon-search" v-model="searchText" class="search-input" >
            </el-input>
            <el-button type="primary" @click="search">检索</el-button>
          </div>

          <div class="services" style="margin-top: 30px; margin-left: 15px; font-size: 25px">
            <span class="service"><a href="http://localhost:8080">数据使用权</a></span>
            <span class="service"><a href="http://localhost:8080">数据所有权</a></span>
            <span class="service"><a href="http://localhost:8080">数据经营权</a></span>
            <span class="service"><a href="http://localhost:8080">数据管理权</a></span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- First two large cards, each occupying half of the row -->
    <el-row :gutter="20" class="ecs-instances">
      <el-col :span="12" v-for="(item, index) in this.firstData" :key="index">
        <el-card shadow="always" class="box-card large-card" style="position: relative;">
          <div slot="header" class="clearfix" style="text-align: center;">
            <span style="position: absolute; top: 15px; left: 30px; font-size: 26px;"><strong>{{item.name}}</strong></span>
<!--            <el-button type="primary" size="small" class="float-right">了解更多</el-button>-->
          </div>
          <div style="position: absolute; top: 70px; left: 30px; font-size: 17px;">资产编号: &nbsp;<span style="color: #8c939d"> {{item.tokenid}}</span></div>
          <div style="position: absolute; top: 100px; left: 30px; font-size: 17px;">所有者：<span style="color: #8c939d">{{item.username}}</span></div>
<!--          <div style="position: absolute; top: 130px; left: 30px; font-size: 17px;">类型：<span style="color: #8c939d">{{item.type}}</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 所有权：<span style="color: #8c939d">{{item.value}}%</span></div>-->
          <div style="position: absolute; top: 130px; left: 30px; font-size: 17px;">
            类型：<span style="color: #8c939d">{{ item.type }}</span>
            <template v-if="item.qtype === '所有权'">
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 所有权：<span style="color: #8c939d">{{ item.value }}%</span>
            </template>
            <template v-else-if="item.qtype === '使用权'">
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 使用权
            </template>
            <template v-else>
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {{ item.qtype }}
            </template>
          </div>
          <div style="position: absolute; top: 170px; left: 30px; font-size: 25px; color: #FF4400FF; display: flex; align-items: center;">
            价格: &nbsp; ¥{{ item.price }}
            <template v-if="item.qtype === '所有权'">/1%</template>
            <span style="margin-left: 50px;">
    <el-rate v-model="item.rating" disabled></el-rate>
  </span>
          </div>

          <!-- 图片部分 -->
          <div class="card-image" style="position: absolute; top: 180px; left: 80px; font-size: 25px;">
            <el-image
                :src="item.picture"
                style="width: 400px; height: 200px; object-fit: cover; margin-top: -400px; margin-left: 400px"
            ></el-image>
          </div>
          <el-button type="warning" class="buy-btn" style="position: absolute; top: 200px; left: 320px; font-size: 25px;" @click="nftdetail(item.tokenid)">查看详情</el-button>
        </el-card>
      </el-col>

<!--      <el-col :span="12">-->
<!--        <el-card shadow="always" class="box-card large-card">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <span style="margin-left: -510px; font-size: 25px">全球外汇市场动态监控数据</span>-->
<!--&lt;!&ndash;            <el-button type="primary" size="small" class="float-right">了解更多</el-button>&ndash;&gt;-->
<!--          </div>-->
<!--          <div style="font-size: 17px; margin-top: 20px; margin-left: -635px">资产编号:<span style="color: #8c939d"> 0083045697FX300122</span></div>-->
<!--          <div style="font-size: 17px; margin-top: 20px; margin-left: -650px">所有者：<span style="color: #8c939d">国际外汇交易研究中心</span></div>-->
<!--          <div style="font-size: 17px; margin-top: 20px; margin-left: -550px">关键词：<span style="color: #8c939d">外汇交易 技术分析 云计算 量化交易</span></div>-->
<!--          <div style="font-size: 20px; margin-top: 20px; margin-left: -560px; color: #FF4400FF">价格: &nbsp ¥300/1%  <span style="margin-left: 20px"> 所有权重：100% </span></div>-->
<!--          &lt;!&ndash; 图片部分 &ndash;&gt;-->
<!--          <div class="card-image">-->
<!--            <el-image-->
<!--                :src="'https://img0.baidu.com/it/u=24410656,1979373796&fm=253&fmt=auto&app=138&f=JPEG?w=755&h=500'"-->
<!--                style="width: 400px; height: 200px; object-fit: cover; margin-top: -400px; margin-left: 400px"-->
<!--            ></el-image>-->
<!--          </div>-->
<!--          <el-button type="warning" class="buy-btn" style="margin-top: 15px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->
    </el-row>

    <!-- Remaining cards, 3 per row with minimal spacing -->
    <el-row :gutter="10" class="rds-instances">
      <el-col :span="8" v-for="(item, index) in this.remainData" :key="index" style="margin-top: 20px">
        <el-card class="box-card small-card" style="position: relative;">
          <div  class="clearfix" style="margin-bottom: 10px" >
            <span style="position: absolute; top: 35px; left: 30px; font-size: 30px;"><strong>{{item.name}}</strong></span>
          </div>
          <div class="truncated-text" style="position: absolute; top: 85px; left: 30px; font-size: 17px; color: #8c939d; text-align: left; white-space: normal; width: calc(100% - 60px)" >{{item.info}}</div>
          <div style="position: absolute; top: 140px; left: 30px; font-size: 17px;  color: #cf9236">资产编号 <span style="position: absolute; text-align: left; margin-left: 165px; white-space: nowrap;">所有者</span> </div>
          <div style="position: absolute; top: 170px; left: 30px; font-size: 17px;">{{item.tokenid}} <span style="position: absolute; text-align: left; margin-left: 30px; white-space: nowrap;">{{item.username}}</span> </div>
<!--          <div style="position: absolute; top: 200px; left: 30px; font-size: 17px; color: #FF4400FF">价格: &nbsp ¥{{item.price}}/1%  <span style="position: absolute; text-align: left; margin-left: 90px; white-space: nowrap;"> 所有权重：{{item.value}}% </span></div>-->
          <div style="position: absolute; top: 200px; left: 30px; font-size: 17px; color: #FF4400FF; display: flex; align-items: center;">
            价格: &nbsp; ¥{{ item.price }}
            <template v-if="item.qtype === '所有权'">/1%</template>
            <template v-if="item.qtype === '所有权'">
    <span style="margin-left: 80px; white-space: nowrap; text-align: left;">
      所有权重：{{ item.value }}%
    </span>
            </template>
            <template v-else-if="item.qtype == '使用权'">
              <span style="margin-left: 135px; white-space: nowrap; text-align: left;">
      使用权
    </span>
            </template>
          </div>

          <el-button type="primary" class="buy-btn" style="position: absolute; top: 200px; left: 200px; font-size: 17px;" @click="nftdetail(item.tokenid)">查看详情</el-button>
        </el-card>
      </el-col>

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 22px; margin-left: -295px"><strong>京东商城小米11手机销售记录</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -18px; color: #8c939d">记录京东商城上小米11手机的销售数量、单价和总销售额，包括销售日期和地区信息...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px;  color: #cf9236">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -120px">0095017845CM400321 <span style="margin-left: 140px">京东集团股份有限公司</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 20px; margin-left: -240px; color: #FF4400FF">价格: &nbsp ¥1200/20%  <span style="margin-left: 20px"> 所有权重：100% </span></div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 22px; margin-left: -310px"><strong>全球债券市场利率波动数据</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集涵盖全球主要国家的国债、公司债的利率变动、交易额、到期收益率等数据...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px;  color: #cf9236">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -110px">0095017845CM400321 <span style="margin-left: 140px">全球债券市场分析中心</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 20px; margin-left: -240px; color: #FF4400FF">价格: &nbsp ¥800/25%  <span style="margin-left: 20px"> 所有权重：50% </span></div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 20px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->

<!--    <el-row :gutter="10" class="additional-services">-->
<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 22px; margin-left: -350px"><strong>全球金融衍生品市场数据</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集包含期权、期货、掉期等金融衍生品的交易数据，覆盖全球...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px"> 0028015639DF700416 <span style="margin-left: 170px"> 国际衍生品市场研究机构</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 20px; margin-left: -350px"><strong>跨境支付交易实时监控数据</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集记录了全球跨境支付交易的实时数据，包括交易金额、货币转换率、支付渠道及跨境交易时效等...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px">0059023468CP800571 <span style="margin-left: 170px">全球支付网络安全委员会</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 20px; margin-left: -350px"><strong>国际投资组合风险分析数据</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px">0015026937PA900634 <span style="margin-left: 170px">本数据集涵盖全球投资组合的资产配置、收益率、风险系数等多维度数据，分析不同地区</span> </div>-->
<!--            ...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    &lt;!&ndash; Remaining cards, 3 per row with minimal spacing &ndash;&gt;-->
<!--    <el-row :gutter="10" class="rds-instances">-->
<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 20px; margin-left: -350px"><strong>大宗商品期货市场行情</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集包含全球大宗商品期货市场的价格、成交量、持仓量等信息，涵盖石油、黄...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px">0095017845CM400321 <span style="margin-left: 170px">全球大宗商品期货交易协会</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 20px; margin-left: -350px"><strong>大宗商品期货市场行情</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集包含全球大宗商品期货市场的价格、成交量、持仓量等信息，涵盖石油、黄...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px">0095017845CM400321 <span style="margin-left: 170px">全球大宗商品期货交易协会</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="8">-->
<!--        <el-card class="box-card small-card">-->
<!--          <div  class="clearfix" style="margin-bottom: 10px">-->
<!--            <span style="margin-bottom: 20px; font-size: 20px; margin-left: -350px"><strong>大宗商品期货市场行情</strong></span>-->
<!--          </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 15px; margin-left: -5px; color: #8c939d">本数据集包含全球大宗商品期货市场的价格、成交量、持仓量等信息，涵盖石油、黄...</div>-->
<!--          <div style="margin-bottom: 20px; font-size: 18px; margin-left: -170px">资产编号 <span style="margin-left: 250px">所有者</span> </div>-->
<!--          <div style="margin-bottom: 20px; font-size: 12px; margin-left: -120px">0095017845CM400321 <span style="margin-left: 170px">全球大宗商品期货交易协会</span> </div>-->
<!--          <div style="font-size: 20px; margin-top: 40px; margin-left: -270px; color: #FF4400FF">价格: ¥199/1%   所有权重：100%</div>-->
<!--          <el-button type="primary" class="buy-btn" style="margin-top: 18px">查看详情</el-button>-->
<!--        </el-card>-->
<!--      </el-col>-->
    </el-row>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "CloudServices",
  created(){
    this.load()
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
  },
  data() {
    return {
      tableData: '',
      firstData: '',
      remainData: '',
      total: 0,
      searchText: '',
      selectedCategory: ''
    };
  },
  methods: {
    load(){
      // request.get("/netview",{
      //   params:{
      //     pageNum: this.currentPage,
      //     pageSize: this.pageSize,
      //     search1: this.search1,
      //     search2: this.search2,
      //     search3: this.search3,
      //     search4: this.search4,
      //   }
      request.get("/nftview").then(res =>{
        console.log(res)
        this.tableData = res.data
        this.firstData = this.tableData.slice(0,2)
        this.remainData = this.tableData.slice(2)
        this.total = res.data.total
      })
    },
    nftdetail(tokenid){
      this.$router.push({ path: '/web/nftdetail', query: { tokenid: tokenid } });
    },
    search() {
      console.log(this.selectedCategory)
      request.get("/nftview/nftsearch",{
        params:{
          search: this.selectedCategory,
          key: this.searchText,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.firstData = this.tableData.slice(0,2)
        this.remainData = this.tableData.slice(2)
        this.total = res.data.total
      })
    },
  }
};
</script>

<style scoped>
.truncated-text {
  display: -webkit-box;
  -webkit-line-clamp: 2; /* 显示 3 行 */
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.cloud-services {
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

/* Centering the top CNKI card */
.login-section {
  margin-bottom: 40px;
}

/* Styling for the CNKI card */
.cnki-card {
  background: rgba(255, 255, 255, 0);
  text-align: center;
  padding: 20px;
  font-size: 20px;
}
.cnki-logo {
  height: 50px;
}
.search-bar {
  background: rgba(255, 255, 255, 0);
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0;
  font-size: 20px;
}
.search-select {
  background: rgb(255, 255, 255);
  width: 150px;
  margin-right: 0.1px;
  margin-left: -60px;
}
.search-input {
  width: 400px;
  margin-right: 5px;
  margin-left: 1px;
}
.services {
  margin-top: 10px;

}
.services .service {
  margin-right: 80px;
}

/* First two larger cards */
.ecs-instances, .rds-instances, .additional-services {
  margin-bottom: 20px;
}

.box-card {
  text-align: center;
}

.large-card {
  background: rgba(255, 255, 255, 0.54);
  height: 300px;
}

.small-card {
  background: rgba(255, 255, 255, 0.3);
  height: 300px;
}

.float-right {
  float: right;
}

.buy-btn {
  margin-top: 40px;
  margin-bottom: 10px;
  width: 200px;
  margin-left: 0px;
}
</style>


