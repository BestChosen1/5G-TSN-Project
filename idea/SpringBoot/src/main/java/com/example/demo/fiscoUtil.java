package com.example.demo;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class fiscoUtil {

    public BcosSDK bcosSDK;

    public fiscoUtil() throws Exception {
        // 初始化BcosSDK对象
        String configPath = Objects.requireNonNull(getClass().getClassLoader().getResource("config-example.toml"))
                .getPath();
        bcosSDK = BcosSDK.build(configPath);
    }
}
