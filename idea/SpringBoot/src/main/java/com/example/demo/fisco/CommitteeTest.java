package com.example.demo.fisco;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class CommitteeTest {
// 获取配置文件路径
    public final String configFile = "E:\\BlockChain\\NetworkSecurityInnovationCompetition\\CYLmanage\\idea\\SpringBoot\\src\\main\\resources\\config-example.toml";

    // @Test
    public ArrayList<String> searchBC() throws Exception {
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "E:\\BlockChain\\NetworkSecurityInnovationCompetition\\CYLmanage\\idea\\SpringBoot\\src\\main\\resources\\abi", "");
        //同步方式发送交易

        System.out.println("调用合约查询接口");
        //调用合约查询接口
        List<Object> params = new ArrayList<>();
        params.add("ERC3525");
        // 调用智能合约的select
        TransactionResponse transactionResponse2 = transactionProcessor.sendTransactionAndGetResponseByContractLoader("ERC3525", "0x1b4bd20226cdcdcb01dfb4027081d17438a36330", "name", params);
        // 打印返回值
        List<Object> returnValues2 = transactionResponse2.getReturnObject();
        System.out.println(returnValues2);
        ArrayList arrayList = (ArrayList) returnValues2.get(1);

        return arrayList;
    }

    public void insertBC(String data) throws Exception {
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        System.out.println(5687);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        System.out.println(51234);
        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "E:\\bysj\\CYLmanage\\idea\\SpringBoot\\src\\main\\resources\\abi", "");
        System.out.println(234532543);
        //同步方式发送交易
        // 创建调用交易函数的参数
        String category = "committee";
        String status = "1";

        List<Object> params = new ArrayList<>();
        params.add(category);
        params.add(data);
        params.add(status);
        System.out.println("调用合约插入接口");
        // 调用insert合约
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("Committee", "0x2814dac59bc952ab62259d628ad1f059783a8f41", "insert", params);

        // 打印返回值
        List<Object> returnValues = transactionResponse.getReturnObject();
        if (returnValues != null) {
            for (Object value : returnValues) {
                System.out.println("上链返回值："+value.toString());
            }
        }

    }
}
