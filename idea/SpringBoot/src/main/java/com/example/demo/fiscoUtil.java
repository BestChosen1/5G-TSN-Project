package com.example.demo;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class fiscoUtil {

    public BcosSDK bcosSDK;

    public fiscoUtil() throws Exception {
        // 初始化BcosSDK对象
        bcosSDK = BcosSDK.build("E:\\BlockChain\\NetworkSecurityInnovationCompetition\\CYLmanage\\idea\\SpringBoot\\src\\main\\resources\\config-example.toml");

    }
}
