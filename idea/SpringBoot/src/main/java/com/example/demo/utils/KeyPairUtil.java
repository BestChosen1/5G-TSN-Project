package com.example.demo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyPairUtil {

    // 从配置文件中读取密钥保存路径
    @Value("${key.save.path}")
    private String keySavePath;

    // 生成密钥对
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度
        return keyPairGenerator.generateKeyPair();
    }

    // 保存密钥到指定文件路径
    public void saveKeyToFile(String fileName, Key key) throws IOException {
        // 确保目标路径存在
        Files.createDirectories(Paths.get(keySavePath));

        // 拼接文件完整路径
        String fullPath = Paths.get(keySavePath, fileName).toString();

        // 使用 FileOutputStream 写入 PEM 格式
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
            String pemKey = "-----BEGIN " + (fileName.contains("public") ? "PUBLIC" : "PRIVATE") + " KEY-----\n"
                    + encodedKey.replaceAll("(.{64})", "$1\n") // 每行最多64字符
                    + "\n-----END " + (fileName.contains("public") ? "PUBLIC" : "PRIVATE") + " KEY-----";
            fos.write(pemKey.getBytes());
        }
    }
}


