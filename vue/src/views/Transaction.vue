<template>
  <div class="payment-success">
    <div class="success-box">
      <div class="header">
        <h2>支付成功</h2>
        <p class="subtitle">感谢您的支付！交易信息如下：</p>
      </div>

      <div class="transaction-details">
        <p><strong>交易哈希值：</strong><span class="hash">{{ txHash }}</span></p>
        <p><strong>交易状态：</strong><span class="status">{{ txStatus }}</span></p>
        <p><strong>时间戳：</strong>{{ timestamp }}</p>
        <p><strong>资产编号：</strong>{{ assetId }}</p>
        <p><strong>数据名称：</strong>{{ assetName }}</p>
        <p><strong>数据类型：</strong>{{ dataType }}</p>
        <p><strong>流转权重：</strong>{{ weight }}%</p>
        <p><strong>权属类型：</strong>{{ ownershipType }}</p>
        <div class="transaction-details"><p><strong>已支付：</strong>
       <span class="price">¥{{ totalprice }} </span><img
              src="https://img.icons8.com/color/48/000000/checked--v1.png"
              alt="交易成功"
              style="width: 25px; height: 25px; margin-left: 10px; padding-top: 5px;"
          />
        </p>
          </div>
        <p><strong>区块编号：</strong>{{ blockNumber }}</p>
        <p><strong>IPFS 文件哈希值：</strong><span class="hash">{{ ipfsHash }}</span><!-- 上传私钥文件 -->
          <div class="upload-section">
            <label class="custom-file-upload">
              <input type="file" @change="handleFileUpload" />
              <i class="fas fa-upload"></i> 点击获取数据
            </label>
          </div></p>
      </div>

      <button class="return-btn" @click="returnToHome">返回首页</button>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";

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
    // 随机生成交易哈希
    this.txHash = `0x${Array(64).fill().map(() => Math.floor(Math.random() * 16).toString(16)).join('')}`;
  },
  data() {
    return {
      itemData: {},
      username: '',
      user: '',
      txHash: '0x5e784dd7a9197a1dfbc4e3f64fd1ff2994b7e19ac8f7e5bdfca1d7d7b88a6e2f',
      txStatus: '成功',
      timestamp: '2024-10-15 14:35:22',
      assetId: '0062015234FS20076',
      assetName: '沪深股市每日交易数据',
      dataType: '股票市场数据',
      weight: '15%',
      ownershipType: '所有权',
      blockNumber: '#15743',
      ipfsHash: 'QmTgoJrvbNG7dyE9uGSkmmAXKFVtq6fa7sjy2DLApKquru'
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
    returnToHome() {
      this.$router.push('/web/nftview');
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
  }
};
</script>

<style scoped>
.price-display {
  margin: 10px 0;
  padding: 10px;

}

.price {
  font-size: 20px;
  font-weight: bold;
  color: #4ebf35;
}
.payment-success {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #e0f7fa 0%, #ffeb3b 100%);
  background-image: url("../assets/img/10.png")!important;
}

.success-box {
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
  margin-top: 5px;
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


.hash {
  color: #3f51b5;
  word-break: break-all;
}

.status {
  color: #4caf50;
  font-weight: bold;
}

.return-btn {
  background-color: #4caf50;
  color: white;
  padding: 12px 30px;
  border: none;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.return-btn:hover {
  background-color: #388e3c;
}
</style>
