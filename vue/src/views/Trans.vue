<template>
  <div class="order-confirmation">
    <div class="confirmation-box">
      <div class="header">
        <h2>确认订单信息</h2>
        <p class="subtitle">请核对以下订单信息：</p>
      </div>

<!--      &lt;!&ndash; 用户账户信息 &ndash;&gt;-->
<!--      <div class="user-info">-->
<!--        <p><strong>用户账户：</strong><span>{{ userAccount }}</span></p>-->
<!--      </div>-->

      <!-- 交易信息 -->
      <div class="transaction-details">
        <p><strong>用户名：</strong><span class="hash">{{ username }}</span></p>
        <p><strong>账户地址：</strong><span class="hash">{{ address }}</span></p>
        <p><strong>交易状态：</strong><span class="status">{{ txStatus }}</span></p>
        <p><strong>时间戳：</strong>{{ timestamp }}</p>
        <p><strong>资产编号：</strong>{{ assetId }}</p>
        <p><strong>数据名称：</strong>{{ assetName }}</p>
        <p><strong>数据类型：</strong>{{ dataType }}</p>
        <p><strong>流转权重：</strong>{{ weight }}%</p>
        <p><strong>权属类型：</strong>{{ ownershipType }}</p>

<!--        <p><strong>区块编号：</strong>{{ blockNumber }}</p>-->
<!--        <p><strong>IPFS 文件哈希值：</strong><span class="hash">{{ ipfsHash }}</span></p>-->
      </div>


      <!-- 上传私钥文件 -->
      <div class="upload-section">
        <p><strong>上传私钥文件：</strong></p>
        <label class="custom-file-upload">
          <input type="file" @change="handleFileUpload" />
          <i class="fas fa-upload"></i> 选择文件
        </label>
      </div>

      <div class="transaction-details"><p><strong>总价格：</strong></p></div>
      <!-- 新增总价格显示 -->
      <div class="price-display">
        <p><span class="price">¥{{ totalprice }}</span></p>
      </div>

      <!-- 支付方式 -->
      <div class="payment-methods">
<!--        <p class="subtitle">选择支付方式:</p>-->
        <img src="../assets/img/25.jpg" alt="微信支付" class="pay-icon" />
        <img src="../assets/img/21.jpg" alt="银联支付" class="pay-icon" />
        <img src="../assets/img/26.jpg" alt="支付宝" class="pay-icon" />
      </div>

      <!-- 确认支付按钮 -->
      <button class="confirm-btn" @click="confirmPayment">确认支付</button>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";
import router from "../router";

export default {
  created() {
    // 取出当前点击的商品ID
    // console.log("当前查询的ID是"+this.$route.query.id);
    this.loadData(this.$route.query.tokenid, this.$route.query.transferWeight);
    // 从session中取出登陆者的信息
    let str = sessionStorage.getItem("user") || "{}";
    // 解析str
    this.user = JSON.parse(str);
    // console.log("现在登录的人是"+this.user.username);
    // 随机生成以太坊地址
    this.address = `0x${Array(40).fill().map(() => Math.floor(Math.random() * 16).toString(16)).join('')}`;

    // 随机生成区块编号（模拟一个六位或七位的编号）
    this.blockNumber = `#${Math.floor(Math.random() * 9000000) + 1000000}`;

    // 随机生成 IPFS 文件哈希值（模拟标准的 Qm 开头哈希值）
    const chars = '123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz'; // Base58 编码字符集
    this.ipfsHash = `Qm${Array(44).fill().map(() => chars[Math.floor(Math.random() * chars.length)]).join('')}`;
  },
  data() {
    return {
      itemData: {},
      user: {},
      address: '',
      // userAccount: 'user_account',  // 用户账户
      username: 'jack bob',  // 交易哈希值
      txStatus: '待支付',  // 交易状态
      timestamp: '2024-10-19 12:35:22',  // 时间戳
      assetId: '0062015234FS20076',  // 资产编号
      assetName: '沪深股市每日交易数据',  // 数据名称
      dataType: '股票市场数据',  // 数据类型
      weight: '15%',  // 流转权重
      ownershipType: '所有权',  // 权属类型
      blockNumber: '',  // 区块编号
      ipfsHash: '',  // IPFS 文件哈希值
      totalprice: 0,

    };
  },
  methods: {
    updateTime() {
      const now = new Date();
      this.timestamp = now.toLocaleString('zh-CN', {
        hour12: false,
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
      });
    },
    loadData:function (tokenid, transferWeight){
      request.get('/nftview/one',{
        params:{
          tokenid: tokenid,
        }
      }).then(res => {
        console.log(res);
        this.itemData = res.data;
        this.username = this.user.username;
        this.updateTime();
        this.assetId = this.itemData.tokenid;
        this.assetName = this.itemData.name;
        this.dataType = this.itemData.type;
        this.weight = transferWeight;
        this.totalprice = transferWeight * this.itemData.price
      });
    },
    handleFileUpload(event) {
      // 处理文件上传的逻辑
      const file = event.target.files[0];
      console.log('文件上传: ', file);
    },
    confirmPayment() {
      // 确认支付的逻辑
      router.push({
        path: '/transaction',
        query: {
          tokenid: this.itemData.tokenid,
          transferWeight: this.weight,
        },
      })
    }
  }
};
</script>

<style scoped>
.price-display {
  margin: 20px 0;
  padding: 10px;
  background-color: #e3f2fd;
  border: 1px solid #90caf9;
  border-radius: 5px;
}

.price {
  font-size: 30px;
  font-weight: bold;
  color: #f44336;
}
.order-confirmation {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #e0f7fa 0%, #ffeb3b 100%);
  background-image: url("../assets/img/10.png")!important;
}

.confirmation-box {
  background-color: #fff;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  width: 600px;
  text-align: center;
}

.header h2 {
  color: #4caf50;
  font-size: 28px;
  margin-bottom: 10px;
}

.subtitle {
  color: #607d8b;
  margin-bottom: 30px;
  font-size: 18px;
}

.transaction-details {
  text-align: left;
  margin-bottom: 20px;
}

.transaction-details p {
  font-size: 18px;
  color: #424242;
  margin-bottom: 12px;
}

.transaction-details strong {
  color: #00796b;
}

.hash {
  color: #3f51b5;
  word-break: break-all;
}

.status {
  color: #ff9800;
  font-weight: bold;
}

.upload-section {
  text-align: left;
  margin-bottom: 30px;
}

.upload-section p {
  font-size: 18px;
  color: #424242;
  margin-bottom: 12px;
}

.custom-file-upload {
  display: inline-block;
  padding: 10px 20px;
  cursor: pointer;
  background-color: #00796b;
  color: white;
  border-radius: 5px;
  font-size: 16px;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.custom-file-upload:hover {
  background-color: #004d40;
}

.custom-file-upload i {
  margin-right: 10px;
  font-size: 16px;
}

.custom-file-upload input[type="file"] {
  display: none;
}

.payment-methods {
  margin-bottom: 30px;
}

.pay-icon {
  height: 100px;
  margin-right: 20px;
  width: 150px;
}

.confirm-btn {
  background-color: #4caf50;
  color: white;
  padding: 12px 30px;
  border: none;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.confirm-btn:hover {
  background-color: #388e3c;
}
</style>
