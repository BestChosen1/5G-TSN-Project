package com.example.demo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FileDownloadController {

    // 下载公钥文件
    @GetMapping("/download/{username}_public.pub.pem")
    public ResponseEntity<FileSystemResource> downloadPublicKey(@PathVariable String username) {
        // 构造文件路径
        String filePath = "E:/BlockChain/NetworkSecurityInnovationCompetition/CYLmanage/src/main/resources/keystore/" + username + "_public.pub.pem";
        File file = new File(filePath);

        // 如果文件不存在，返回404
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 返回文件作为下载资源
        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // 下载私钥文件
    @GetMapping("/download/{username}_private.pem")
    public ResponseEntity<FileSystemResource> downloadPrivateKey(@PathVariable String username) {
        String filePath = "E:/BlockChain/NetworkSecurityInnovationCompetition/CYLmanage/src/main/resources/keystore/" + username + "_private.pem";
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
