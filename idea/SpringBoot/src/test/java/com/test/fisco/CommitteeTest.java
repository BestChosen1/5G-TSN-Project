package com.test.fisco;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommitteeTest {
    // 获取配置文件路径
    public final String configFile = "src/main/resources/config-example.toml";

    @Test
    public void test() throws Exception {
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "src/main/resources/abi/", "");

        List<String> stringList = Arrays.asList("{'no':1,'id':2020000277,'name':王晨涛,'college':01,'stuClass':机械2001,'sex':男,'cardId':132102200203210512,'nation':汉族,'home':山西吕梁,'birth':2002-04-21,'joinDate':2015-05-09,'memId':null,'phone':13488456455,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:03'}",
"{'no':2,'id':2020000274,'name':周存僚,'college':01,'stuClass':机械2001,'sex':男,'cardId':311525200205028000,'nation':汉族,'home':河南固始,'birth':2002-05-09,'joinDate':2015-11-08,'memId':null,'phone':18383468551,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':2,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:04'}",
"{'no':3,'id':2020000273,'name':高翔瑜,'college':01,'stuClass':机械2001,'sex':男,'cardId':111121200103201000,'nation':汉族,'home':河北衡水,'birth':2003-04-20,'joinDate':2019-05-17,'memId':3138541 ,'phone':18703186610,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:05'}",
"{'no':4,'id':2019000043,'name':刘志豪,'college':01,'stuClass':机械2001,'sex':男,'cardId':311321122802023351,'nation':汉族,'home':山西朔州,'birth':1998-09-02,'joinDate':2016-11-01,'memId':4064443,'phone':17777785453,'zhtj':1,'volunteer':0,'petition':0,'problem':0,'status':3,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:06'}",
"{'no':5,'id':2020000051,'name':吴逸飞,'college':01,'stuClass':机械2002,'sex':男,'cardId':12112120001212381X,'nation':汉族,'home':江苏镇江,'birth':2000-12-12,'joinDate':2014-05-04,'memId':null,'phone':13585556336,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:07'}",
"{'no':6,'id':2020000053,'name':吴文聪,'college':01,'stuClass':机械2002,'sex':男,'cardId':160121200102211211,'nation':汉族,'home':江西南昌,'birth':2001-02-21,'joinDate':2015-12-24,'memId':null,'phone':18364835058,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:08'}",
"{'no':7,'id':2020000034,'name':王骏杰,'college':01,'stuClass':机械2002,'sex':男,'cardId':110305200203012313,'nation':汉族,'home':河北张家口,'birth':2002-04-03,'joinDate':2016-11-30,'memId':null,'phone':18831307178,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':4,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:09'}",
"{'no':8,'id':2020000043,'name':张浩,'college':01,'stuClass':机械2002,'sex':男,'cardId':132610200203232012,'nation':汉族,'home':山西临汾,'birth':2002-07-27,'joinDate':2017-05-04,'memId':4116923,'phone':18534888645,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:10'}",
"{'no':9,'id':2020001833,'name':苏婷,'college':03,'stuClass':自动化2001,'sex':女,'cardId':610323200102132228,'nation':汉族,'home':陕西咸阳,'birth':2003-02-17,'joinDate':2021-04-30,'memId':202114,'phone':15855105735,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:11'}",
"{'no':10,'id':2020001834,'name':石皓辰,'college':03,'stuClass':自动化2001,'sex':男,'cardId':61012520021110001X,'nation':汉族,'home':陕西西安,'birth':2002-11-10,'joinDate':2016-04-15,'memId':null,'phone':13775173358,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:12'}",
"{'no':11,'id':2020003618,'name':李昕海,'college':03,'stuClass':自动化2001,'sex':男,'cardId':130221200103116515,'nation':汉族,'home':山西大同,'birth':2001-07-13,'joinDate':2014-11-20,'memId':null,'phone':17836088817,'zhtj':1,'volunteer':0,'petition':1,'problem':0,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:13'}",
"{'no':12,'id':2020003837,'name':王盈喜,'college':03,'stuClass':自动化2001,'sex':男,'cardId':310181200003236010,'nation':汉族,'home':河南洛阳,'birth':2000-04-24,'joinDate':2013-11-13,'memId':null,'phone':18336770455,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':5,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:14'}",
"{'no':13,'id':2020005347,'name':黄江南,'college':03,'stuClass':自动化2001,'sex':男,'cardId':311102200102035631,'nation':汉族,'home':湖南永州,'birth':2001-10-20,'joinDate':2018-05-04,'memId':3153169,'phone':18507471568,'zhtj':1,'volunteer':0,'petition':0,'problem':0,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:15'}",
"{'no':14,'id':2019001944,'name':李旌劼,'college':03,'stuClass':自动化2002,'sex':男,'cardId':110105200103165018,'nation':汉族,'home':上海,'birth':2001-04-16,'joinDate':2018-05-29,'memId':1024158,'phone':13003180300,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:16'}",
"{'no':15,'id':2020001836,'name':张嘉诚,'college':03,'stuClass':自动化2002,'sex':男,'cardId':110325200205015551,'nation':汉族,'home':河北邯郸,'birth':2002-05-01,'joinDate':2016-05-31,'memId':null,'phone':17350657100,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:17'}",
"{'no':16,'id':2020001837,'name':李嘉怡,'college':03,'stuClass':自动化2002,'sex':女,'cardId':132613200202162821,'nation':汉族,'home':山西临汾,'birth':2002-09-16,'joinDate':2017-04-24,'memId':4118348,'phone':18103430516,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:18'}",
"{'no':17,'id':2020001838,'name':赵倩倩,'college':03,'stuClass':自动化2002,'sex':女,'cardId':130622200211253235,'nation':汉族,'home':山西朔州,'birth':2002-11-25,'joinDate':2015-11-25,'memId':null,'phone':13683457788,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':2,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:19'}",
"{'no':18,'id':2020001839,'name':肖昕辰,'college':03,'stuClass':自动化2002,'sex':女,'cardId':130202200111131022,'nation':汉族,'home':山西大同,'birth':2001-11-17,'joinDate':2017-04-20,'memId':4021221,'phone':17703417740,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:20'}",
"{'no':19,'id':2020001840,'name':高馨,'college':03,'stuClass':自动化2002,'sex':女,'cardId':131102200003160105,'nation':汉族,'home':山西吕梁,'birth':2000-04-16,'joinDate':2015-05-04,'memId':null,'phone':18138583510,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:21'}",
"{'no':20,'id':2020001841,'name':韩嘉伟,'college':03,'stuClass':自动化2002,'sex':男,'cardId':132621200108183210,'nation':汉族,'home':山西临汾,'birth':2001-08-18,'joinDate':2016-12-20,'memId':null,'phone':18034387471,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:22'}",
"{'no':21,'id':2019001262,'name':田少辰,'college':02,'stuClass':材科1901,'sex':男,'cardId':610381200012250016,'nation':汉族,'home':陕西咸阳,'birth':2000-12-25,'joinDate':2016-04-07,'memId':null,'phone':18555107851,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:23'}",
"{'no':22,'id':2019001259,'name':颜昊,'college':02,'stuClass':材科1901,'sex':男,'cardId':310221200103082611,'nation':汉族,'home':湖南株洲,'birth':2001-04-08,'joinDate':2016-04-30,'memId':082113,'phone':14785455663,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:24'}",
"{'no':23,'id':2019001263,'name':曾梓恒,'college':02,'stuClass':材科1901,'sex':男,'cardId':652201200003100812,'nation':汉族,'home':新疆乌鲁木齐,'birth':2000-07-10,'joinDate':2018-12-12,'memId':064130,'phone':18158858116,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:25'}",
"{'no':24,'id':2019001368,'name':任溥文,'college':02,'stuClass':材科1901,'sex':男,'cardId':132128200112260051,'nation':汉族,'home':山西吕梁,'birth':2001-12-26,'joinDate':2014-12-26,'memId':null,'phone':18138487467,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:26'}",
"{'no':25,'id':2019001265,'name':徐浩宇,'college':02,'stuClass':材科1902,'sex':男,'cardId':110523200108211612,'nation':汉族,'home':河北邢台,'birth':2001-08-21,'joinDate':2015-06-09,'memId':null,'phone':18731587505,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:27'}",
"{'no':26,'id':2019001266,'name':史雨萌,'college':02,'stuClass':材科1902,'sex':女,'cardId':130322200201118363,'nation':汉族,'home':山西长治,'birth':2002-03-13,'joinDate':2015-03-13,'memId':null,'phone':17836088536,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':4,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:28'}",
"{'no':27,'id':2019001267,'name':白宁,'college':02,'stuClass':材科1902,'sex':女,'cardId':132125200103031566,'nation':汉族,'home':山西吕梁,'birth':2001-04-07,'joinDate':2014-12-25,'memId':null,'phone':17836086557,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:29'}",
"{'no':28,'id':2020002402,'name':赵树汁,'college':05,'stuClass':物联网2002,'sex':男,'cardId':510821200001120116,'nation':汉族,'home':四川广元,'birth':2000-03-19,'joinDate':2015-05-04,'memId':null,'phone':18150438655,'zhtj':1,'volunteer':0,'petition':1,'problem':0,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:30'}",
"{'no':29,'id':2020002403,'name':潘朋伟,'college':05,'stuClass':物联网2002,'sex':男,'cardId':610326200003113511,'nation':汉族,'home':陕西咸阳,'birth':2000-04-11,'joinDate':2015-12-25,'memId':null,'phone':13385105538,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:31'}",
"{'no':30,'id':2020002679,'name':郭燕,'college':05,'stuClass':物联网2002,'sex':女,'cardId':130325200205010033,'nation':汉族,'home':山西寿阳,'birth':2002-05-03,'joinDate':2015-05-04,'memId':null,'phone':18340681488,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:32'}",
"{'no':31,'id':2020006219,'name':郭鹏,'college':05,'stuClass':物联网2002,'sex':男,'cardId':13231120010222001X,'nation':汉族,'home':山西平遥,'birth':2001-09-29,'joinDate':2014-12-30,'memId':null,'phone':18734475888,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':3,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:33'}",
"{'no':32,'id':2020002345,'name':陈琳,'college':05,'stuClass':物联网2001,'sex':男,'cardId':120221200205121032,'nation':汉族,'home':天津宁河,'birth':2002-05-12,'joinDate':2016-06-18,'memId':null,'phone':18155786088,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:34'}",
"{'no':33,'id':2020002346,'name':裴玉,'college':05,'stuClass':物联网2001,'sex':女,'cardId':111121200111223321,'nation':汉族,'home':河北衡水,'birth':2001-11-22,'joinDate':2015-05-20,'memId':null,'phone':13133516387,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:35'}",
"{'no':34,'id':2020002347,'name':王仲媛,'college':05,'stuClass':物联网2001,'sex':女,'cardId':11100220010301332X,'nation':汉族,'home':河北廊坊,'birth':2001-07-03,'joinDate':2013-10-18,'memId':null,'phone':13603364587,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:36'}",
"{'no':35,'id':2020002348,'name':魏媛昕,'college':05,'stuClass':物联网2001,'sex':女,'cardId':130322200101252028,'nation':汉族,'home':山西长治,'birth':2003-01-25,'joinDate':2016-10-07,'memId':null,'phone':18334855570,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:37'}",
"{'no':36,'id':2020002349,'name':闫洁,'college':05,'stuClass':物联网2001,'sex':女,'cardId':130602200210222632,'nation':汉族,'home':山西朔州,'birth':2002-10-29,'joinDate':2014-05-13,'memId':null,'phone':18838386586,'zhtj':1,'volunteer':1,'petition':1,'problem':1,'status':1,'notes':无,'editor':1001,'updateTime':'2024-05-11 22:46:38'}");
              
        for (String str : stringList) {
            System.out.println(str);
                //同步方式发送交易
        // 创建调用交易函数的参数
        String category = "committee";
//        String data =str;
        String status = "1";

        List<Object> params = new ArrayList<>();
        params.add(category);
        params.add(str);
        params.add(status);
//
        // 调用record合约，调用函数名为『addRecord』，函数参数类型为params
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("Committee", "0x2814dac59bc952ab62259d628ad1f059783a8f41", "insert", params);

        // 打印返回值
        List<Object> returnValues = transactionResponse.getReturnObject();
        if (returnValues != null) {
            for (Object value : returnValues) {
                System.out.println("上链返回值："+value.toString());
            }
        }}


    }
}
