package com.example.demo.controller;

import com.ERC3525;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.User;
import com.example.demo.entity.UserLink;
import com.example.demo.entity.Userkey;
import com.example.demo.fisco.HelloWorld;
import com.example.demo.fiscoUtil;
import com.example.demo.mapper.UserkeyMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.KeyPairUtil;
import com.example.demo.utils.TokenUtils;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.KeyPair;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    UserkeyMapper userkeyMapper;

    @Autowired
    private KeyPairUtil keyPairUtil;

    @Value("${key.save.path}")
    private String keySavePath; // 从配置文件获取路径

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res != null)
        {
            return Result.error("-1","用户名已重复");
        }


        try {
            // 生成密钥对
            KeyPair keyPair = KeyPairUtil.generateKeyPair();


            // 获取公钥和私钥
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());



            System.out.println("Public Key: " + publicKey);
            System.out.println("Private Key: " + privateKey);

            // 保存密钥到文件
            String publicFileName = user.getUsername() + "_public.pub.pem";
            String privateFileName = user.getUsername() + "_private.pem";
            keyPairUtil.saveKeyToFile(publicFileName, keyPair.getPublic());
            keyPairUtil.saveKeyToFile(privateFileName, keyPair.getPrivate());

            user.setPublickey(publicKey);

            // 返回响应
            // return new UserResponse(username, publicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Error generating key pair", e);
        }

        user.setRole(1);
        System.out.println("user: " + user);
        userMapper.insert(user);
        res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));

        UserLink userLink = new UserLink();
        userLink.setPublickeyLink("E:/BlockChain/NetworkSecurityInnovationCompetition/CYLmanage/src/main/resources/keystore/"+res.getUsername() + "_public.pub.pem");
        userLink.setPrivatekeyLink("E:/BlockChain/NetworkSecurityInnovationCompetition/CYLmanage/src/main/resources/keystore/"+res.getUsername() + "_private.pem");
        userLink.setRole(res.getRole());
        userLink.setUsername(res.getUsername());
        userLink.setId(res.getId());

        System.out.println("userLink: " + userLink);

        return Result.success(userLink);
    }



    @CrossOrigin
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) throws Exception {
        System.out.println("lalalalalala");
        fiscoUtil fiscoutil = new fiscoUtil();
        Client client = fiscoutil.bcosSDK.getClient(Integer.valueOf(1));
//
//
        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();
        System.out.println("blockNumber: " + blockNumber);
        // 向群组1部署HelloWorld合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        HelloWorld helloWorld = HelloWorld.deploy(client, cryptoKeyPair);

        // 调用HelloWorld合约的get接口
        String getValue = helloWorld.get();
        System.out.println("getValue: " + getValue);

        // 调用HelloWorld合约的set接口
        TransactionReceipt receipt = helloWorld.set("Hello, fisco");
        System.out.println("receipt: " + receipt);
        System.out.println("getValue: " + helloWorld.get());

        System.out.println("HAHAHAHAHAHAHAHA");
         User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
         if(res == null)
         {
             return Result.error("-1","用户名或密码错误");
         }
         String token = TokenUtils.genToken(res);
         res.setToken(token);
         LoginUser loginuser = new LoginUser();
         loginuser.addVisitCount();

//        ipfsUtil ipfsutil = new ipfsUtil();
//        byte[] data = "hello world".getBytes();
//        String hash = ipfsutil.uploadToIpfs(data);
//        System.out.println(hash);


// 向群组1部署HelloWorld合约
//        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
//        ERC3525 erc3525 = ERC3525.deploy(client, cryptoKeyPair, "1", "1", BigInteger.valueOf(1));
//        String name = erc3525.name();
//        System.out.println(name);


//        System.out.println("调用合约查询接口");
//        //调用合约查询接口
//        List<Object> params = new ArrayList<>();
//        params.add("ERC3525");
//        // 调用智能合约的select
//        TransactionResponse transactionResponse2 = fiscoutil.processor.sendTransactionAndGetResponseByContractLoader("ERC3525", "0x5dff488527c487ceec6a2b21310d65a66295ca46", "totalSupply", params);
//        // 打印返回值
//        List<Object> returnValues2 = transactionResponse2.getReturnObject();
//        System.out.println(returnValues2);
//        ArrayList arrayList = (ArrayList) returnValues2.get(1);
//        System.out.println(arrayList);

        return Result.success(res);
    }
    @PostMapping
    public Result<?> save(@RequestBody User user){
        if(user.getPassword() == null){
            user.setPassword("abc123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
    @PutMapping("/password")
    public  Result<?> password( @RequestParam Integer id,
                              @RequestParam String password2){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        User user = new User();
        user.setPassword(password2);
        userMapper.update(user,updateWrapper);
        return Result.success();
    }
    @PutMapping
    public  Result<?> update(@RequestBody User user){
//        System.out.println(user);
        userMapper.updateById(user);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public  Result<?> deleteBatch(@RequestBody List<Integer> ids){
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        userMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrappers.like(User::getRealName,search);
        }
        wrappers.like(User::getRole,2);
        Page<User> userPage =userMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
        return Result.success(userPage);
    }
    @GetMapping("/usersearch")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4){
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search1)){
            wrappers.like(User::getId,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(User::getRealName,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(User::getPhone,search3);
        }
        if(StringUtils.isNotBlank(search4)){
            // wrappers.like(User::getCommittee,search4);
        }
//        wrappers.like(User::getRole,2);
        Page<User> userPage =userMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
        return Result.success(userPage);
    }
}
